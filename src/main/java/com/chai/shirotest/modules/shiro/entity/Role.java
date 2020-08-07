package com.chai.shirotest.modules.shiro.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Data
@Getter
public class Role {
    @Id
    private Integer roleId;
    private String roleName;
}
