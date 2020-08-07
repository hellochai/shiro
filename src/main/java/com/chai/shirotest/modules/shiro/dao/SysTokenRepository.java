package com.chai.shirotest.modules.shiro.dao;

import com.chai.shirotest.modules.shiro.entity.SysToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * 将token写入redis
 */
@Mapper
@Component
public interface SysTokenRepository {
    SysToken findByUserId(Integer userId);

    Integer updateToken(SysToken tokenEntity);

    SysToken findByToken(String accessToken);

    void insertLoginToken(SysToken token);
}
