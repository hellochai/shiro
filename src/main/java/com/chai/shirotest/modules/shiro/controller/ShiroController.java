package com.chai.shirotest.modules.shiro.controller;

import com.chai.shirotest.modules.shiro.DTO.LoginDTO;
import com.chai.shirotest.modules.shiro.entity.User;
import com.chai.shirotest.modules.shiro.service.ShiroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
@Slf4j
@Api(value = "test")
public class ShiroController {
    private final ShiroService shiroService;

    public ShiroController(ShiroService shiroService){
        this.shiroService = shiroService;
    }

    /**
     * 登录
     */

    @ApiOperation(value = "登陆", notes = "参数:用户名 密码")
    @PostMapping("/sys/login")
    public Map<String, Object> login(@RequestBody @Validated LoginDTO loginDTO, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>();
        if (bindingResult.hasErrors()) {
            result.put("status", 400);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        }

        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        //用户信息
        User user = shiroService.findByUsername(username);
        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(password)) {
            result.put("status", 400);
            result.put("msg", "账号或密码有误");
        } else {
            //生成token，并保存到数据库
            result = shiroService.createToken(user.getUserId());
            result.put("status", 200);
            result.put("msg", "登陆成功");
        }
        return result;
    }

    /**
     * 退出
     */
    @ApiOperation(value = "登出", notes = "参数:token")
    @PostMapping("/sys/logout")
    public Map<String, Object> logout(@RequestHeader("token")String token) {
        Map<String, Object> result = new HashMap<>();
        shiroService.logout(token);
        result.put("status", 200);
        result.put("msg", "您已安全退出系统");
        return result;
    }
}
