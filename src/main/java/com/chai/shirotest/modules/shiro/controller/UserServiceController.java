package com.chai.shirotest.modules.shiro.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: fchai
 * @Date: 2020/8/6 16:45
 */
@RestController
@Api(value = "")

public class UserServiceController {

    @GetMapping("/user/add")
    @RequiresPermissions("select")
    String userAdd(){
        return "Success";
    }
}
