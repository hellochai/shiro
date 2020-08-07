package com.chai.shirotest.modules.shiro.dao;

import com.chai.shirotest.modules.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Mapper
public interface UserRepository {
    User findByUsername(String username);

    User findByUserId(Integer userId);
}
