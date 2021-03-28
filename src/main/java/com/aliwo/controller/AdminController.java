package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.Admin;
import com.aliwo.entity.request.PasswordVO;
import com.aliwo.entity.request.UserLoginRequest;
import com.aliwo.service.AdminService;
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
        if (Strings.isEmpty(adminLoginRequest.getUsername())){
            return ServerResponse.ofError("用户名非空，请填写用户名！！！");
        }
        if (Strings.isEmpty(adminLoginRequest.getPassword())){
            return ServerResponse.ofError("密码非空，请填写用户名！！！");
        }
        Map<String, Object> map = new HashMap();
        Admin admin = adminService.adminLogin(adminLoginRequest.getUsername(), adminLoginRequest.getPassword());
        if (null == admin){
            return ServerResponse.ofError("没有查询到相应用户，请重新登录");
        }
        String token = tokenService.getToken(admin);
        if (null != admin && !StringUtils.isEmpty(token)) {
            // 密码隐藏起来
            map.put("admin", admin);
            map.put("token", token);
            return ServerResponse.ofSuccess(map);
        }
        LOG.error("用户名或密码错误");
        return ServerResponse.ofError("用户名或密码错误!");
    }
    /**
     * 修改管理员信息 个人中心使用
     *
     * @param admin
     * @return ServerResponse
     * token 验证现在不能用后续完善
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    //@UserLoginToken
    public ServerResponse modifyAdminer(@RequestBody Admin admin) {
        if (StringUtils.isEmpty(admin.getTelephone())){
            return ServerResponse.ofError("手机号必填!");
        }
        if (StringUtils.isEmpty(admin.getEmail())){
            return ServerResponse.ofError("邮箱必填");
        }
        // 修改操作
        return adminService.updateById(admin) ? ServerResponse.ofSuccess("修改成功") : ServerResponse.ofError("修改失败");
    }


    /**
     * 管理员修改密码,修改密码使用
     *
     * @param passwordVO
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ServerResponse updateTeacherPass(@RequestBody PasswordVO passwordVO) {
        QueryWrapper<Admin> wrapper = new QueryWrapper();
        wrapper.eq("id", passwordVO.getId());
        wrapper.eq("password", passwordVO.getOldPass());
        Admin admin = adminService.getOne(wrapper);
        if (null == admin) {
            return ServerResponse.ofError("旧密码错误");
        }
        // 否则进入修改密码流程
        admin.setPassword(passwordVO.getNewPass());
        boolean b = adminService.updateById(admin);
        if (b) {
            return ServerResponse.ofSuccess("密码修改成功");
        }
        return ServerResponse.ofError("密码更新失败");
    }

}
