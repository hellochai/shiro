package com.chai.shirotest.modules.shiro.entity;

import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import lombok.Data;

/**
 * @Description:
 * @Author: fchai
 * @Date: 2020/8/7 14:21
 */
@Data
public class RolePermission {
    private Integer id;
    private Integer role_id;
    private Integer permission_id;
}
