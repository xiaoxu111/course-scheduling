package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 19:42
 * 项目名:course-scheduling-system
 * Description:学生端登录请求
 * Version: 1.0
 **/
@Data
public class StudentLoginRequest {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
