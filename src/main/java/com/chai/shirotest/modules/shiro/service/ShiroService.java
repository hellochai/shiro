package com.chai.shirotest.modules.shiro.service;

import com.chai.shirotest.modules.shiro.entity.SysToken;
import com.chai.shirotest.modules.shiro.entity.User;

import java.util.Map;

public interface ShiroService {
    User findByUsername(String username);
    Map<String, Object> createToken(Integer userId);
    void logout(String token);

    SysToken findByToken(String accessToken);

    User findByUserId(Integer userId);
}
