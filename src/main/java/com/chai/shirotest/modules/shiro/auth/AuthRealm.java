package com.chai.shirotest.modules.shiro.auth;


import com.chai.shirotest.modules.shiro.dao.PermissionRepository;
import com.chai.shirotest.modules.shiro.entity.*;
import com.chai.shirotest.modules.shiro.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;


@Component
@Service
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    /**
     * 授权 获取用户的角色和权限
     *@param  [principals]
     *@return org.apache.shiro.authz.AuthorizationInfo
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        User user = (User) principals.getPrimaryPrincipal();
        //Integer userId = user.getUserId();
        //2.添加角色和权限
//        for (Role role : user.getRoles()) {
//            //2.1添加角色
//            simpleAuthorizationInfo.addRole(role.getRoleName());
//            for (Permission permission : role.getPermissions()) {
//                //2.1.1添加权限
//                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
//            }
//        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<Permission> perms;
        // 系统管理员拥有最高权限
        List<UserRolePermission> permissions = null;
        if (User.SUPER_ADMIN == user.getUserId()) {
            perms = permissionRepository.getAllPerms();
            for (Permission userRolePermission : perms
            ) {
                info.addStringPermission(userRolePermission.getPermission());
            }
        } else {
            permissions = permissionRepository.getUserPerms(user.getUserId());
            for (UserRolePermission userRolePermission : permissions
            ) {
                info.addStringPermission(userRolePermission.getPermission());
            }
        }
        Set<String> roles = new HashSet<String>();

        // 依据用户id返回角色信息
        List<UserRoleName> roleNames = permissionRepository.getRolesByUserId(user.getUserId());
        for (UserRoleName role :
                roleNames) {
            roles.add(role.getRoleName());
        }
        info.setRoles(roles);
        // 返回权限
        return info;
    }

    @Override
    /**
     * 认证 判断token的有效性
     *@param  [token]
     *@return org.apache.shiro.authc.AuthenticationInfo
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token，既前端传入的token
        String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity = shiroService.findByToken(accessToken);
        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().before(new Date())) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        User user = shiroService.findByUserId(tokenEntity.getUserId());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        return info;
    }
}
