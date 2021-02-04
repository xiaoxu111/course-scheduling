package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.Teacher;
import com.aliwo.entity.request.UserLoginRequest;
import com.aliwo.service.TeacherService;
import com.aliwo.service.impl.TokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * package_name:com.aliwo.controller
 *
 * @author:徐亚远 Date:2021/2/2 12:53
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TokenService tokenService;

    /**
     * @param userLoginRequest
     * @return ServerResponse
     * 讲师登录controller
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse TeacherLogin(@RequestBody UserLoginRequest userLoginRequest) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_no", userLoginRequest.getUsername());
        // 先查询是否有该账号
        Teacher teacher2 = teacherService.getOne(wrapper);
        if (null == teacher2) {
            return ServerResponse.ofError("账号不存在");
        } else if (0 != teacher2.getStatus()) {
            return ServerResponse.ofError("账号状态异常，请联系管理员");
        }
        // 登录,使用编号登录或者用户名或者真实姓名
        Teacher teacher = teacherService.teacherLogin(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        String token = tokenService.getToken(teacher);
        if (null != teacher && Strings.isNotEmpty(token)) {
            // 允许登录
            teacher.setPassword("");
            map.put("teacher", teacher);
            map.put("token", token);
            return ServerResponse.ofSuccess(map);
        }
        // 否则一律视为密码错误
        return ServerResponse.ofError("密码错误");
    }

}