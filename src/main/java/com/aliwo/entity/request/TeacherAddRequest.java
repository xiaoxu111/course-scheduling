package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 19:48
 * 项目名:course-scheduling-system
 * Description:管理员添加讲师功能的封装体
 * Version: 1.0
 **/
@Data
public class TeacherAddRequest {

    /**
     * 教师编号
     */
    private String teacherNo;

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
     * 支撑
     */
    private String jobtitle;

    /**
     *
     */
    private String teach;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String address;

    /**
     * 年龄
     */
    private Integer age;
}
