package com.chai.shirotest.modules.shiro.DTO;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.Data;

@Data
public class LoginDTO {
//    @NotBlank(message="用户名不能为空")
    private String username;
//    @NotBlank(message="密码不能为空")
    private String password;
}
