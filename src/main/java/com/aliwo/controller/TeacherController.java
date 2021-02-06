package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.Teacher;
import com.aliwo.entity.request.PasswordVO;
import com.aliwo.entity.request.UserLoginRequest;
import com.aliwo.service.TeacherService;
import com.aliwo.service.impl.TokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
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
 * @author:xuyy19 Date:2021/2/2 12:53
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
            map.put("teacher", teacher);
            map.put("token", token);
            return ServerResponse.ofSuccess(map);
        }
        // 否则一律视为密码错误
        return ServerResponse.ofError("密码错误");
    }

    /**
     * 修改讲师信息 个人中心使用
     *
     * @param teacher
     * @return ServerResponse
     * token 验证现在不能用后续完善
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    //@UserLoginToken
    public ServerResponse modifyTeacher(@RequestBody Teacher teacher) {
        if (StringUtils.isEmpty(teacher.getTelephone())){
            return ServerResponse.ofError("手机号必填!");
        }
        if (StringUtils.isEmpty(teacher.getEmail())){
            return ServerResponse.ofError("邮箱必填");
        }
        // 修改操作
        return teacherService.updateById(teacher) ? ServerResponse.ofSuccess("修改成功") : ServerResponse.ofError("修改失败");
    }

    /**
     * 讲师修改密码
     *
     * @param passwordVO
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ServerResponse updateTeacherPass(@RequestBody PasswordVO passwordVO) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper();
        wrapper.eq("id", passwordVO.getId());
        wrapper.eq("password", passwordVO.getOldPass());
        Teacher teacher = teacherService.getOne(wrapper);
        if (null == teacher) {
            return ServerResponse.ofError("旧密码错误");
        }
        // 否则进入修改密码流程
        teacher.setPassword(passwordVO.getNewPass());
        boolean b = teacherService.updateById(teacher);
        if (b) {
            return ServerResponse.ofSuccess("密码修改成功");
        }
        return ServerResponse.ofError("密码更新失败");
    }


}
