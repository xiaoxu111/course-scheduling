package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.Admin;
import com.aliwo.entity.request.UserLoginRequest;
import com.aliwo.service.AdminService;
import com.aliwo.service.impl.TokenService;
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
        if (null != admin) {
            String token = tokenService.getToken(admin);
            // 密码隐藏起来
            admin.setPassword("");
            map.put("admin", admin);
            map.put("token", token);
            return ServerResponse.ofSuccess(map);
        }
        return ServerResponse.ofError("用户名或密码错误!");
    }


}
