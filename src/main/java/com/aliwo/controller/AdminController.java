package com.aliwo.controller;

import com.aliwo.common.GlobalExceptionHandler;
import com.aliwo.common.ServerResponse;
import com.aliwo.entity.Admin;
import com.aliwo.entity.request.UserLoginRequest;
import com.aliwo.service.AdminService;
import com.aliwo.service.impl.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy19 Date:2021/1/24 13:59
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;

    @Autowired
    private TokenService tokenService;

    /**
     * 管理员登录
     *
     * @param adminLoginRequest
     * @return
     */
    @PostMapping("/login")
    public com.aliwo.common.ServerResponse adminLogin(@RequestBody UserLoginRequest adminLoginRequest) {
        Map<String, Object> map = new HashMap();
        Admin admin = adminService.adminLogin(adminLoginRequest.getUsername(), adminLoginRequest.getPassword());
        String token = tokenService.getToken(admin);
        if (null != admin && !StringUtils.isEmpty(token)) {
            // 密码隐藏起来
            admin.setPassword("");
            map.put("admin", admin);
            map.put("token", token);
            return ServerResponse.ofSuccess(map);
        }
        LOG.error("用户名或密码错误");
        return ServerResponse.ofError("用户名或密码错误!");
    }


}
