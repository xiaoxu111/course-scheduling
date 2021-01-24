package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 19:43
 * 项目名:course-scheduling-system
 * Description:学生端注册请求
 * Version: 1.0
 **/
@Data
public class StudentRegisterRequest {

    /**
     * 学号由系统给学生生成，学生通过完善个人信息进行填写其它字段
     */
    private String studentNo;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 年级
     */
    private String grade;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;
}
