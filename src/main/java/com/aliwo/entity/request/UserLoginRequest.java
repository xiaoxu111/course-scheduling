package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 19:53
 * 项目名:course-scheduling-system
 * Description:封装用户(管理员，讲师)登录请求体
 * Version: 1.0
 **/
@Data
public class UserLoginRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 1管理员，2讲师
     */
    private Integer type;
}
