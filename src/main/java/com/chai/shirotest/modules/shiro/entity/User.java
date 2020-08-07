package com.chai.shirotest.modules.shiro.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class User {
    public static final Object SUPER_ADMIN = 1;
    @Id
    private Integer userId;
    private String username;
    private String password;
}

