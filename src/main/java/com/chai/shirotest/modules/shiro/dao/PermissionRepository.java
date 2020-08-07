package com.chai.shirotest.modules.shiro.dao;

import com.chai.shirotest.modules.shiro.entity.Permission;
import com.chai.shirotest.modules.shiro.entity.Role;
import com.chai.shirotest.modules.shiro.entity.UserRoleName;
import com.chai.shirotest.modules.shiro.entity.UserRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PermissionRepository {
    List<Permission> getAllPerms();

    List<UserRolePermission> getUserPerms(Integer userId);
    List<UserRoleName> getRolesByUserId(Integer userId);

}
