package com.chai.shirotest.modules.shiro.entity;

import lombok.Data;

/**
 * @Description:
 * @Author: fchai
 * @Date: 2020/8/7 16:26
 */

@Data
public class UserRolePermission {
    private Integer userId;
    private String roleName;
    private String userName;
    private String permission;
    private String permissionName;
}
