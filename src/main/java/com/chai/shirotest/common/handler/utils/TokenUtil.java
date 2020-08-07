package com.chai.shirotest.common.handler.utils;

import javax.servlet.http.HttpServletRequest;

public class TokenUtil {

    public static String getRequestToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return token;
    }
}
