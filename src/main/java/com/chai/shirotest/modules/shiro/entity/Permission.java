package com.chai.shirotest.modules.shiro.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Permission {
    @Id
    private Integer permissionId;
    private String permissionName;
    private String permission;
}
