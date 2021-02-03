package com.aliwo.service;

import com.aliwo.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * package_name:com.aliwo.service
 *
 * @author:徐亚远 Date:2021/2/2 14:22
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface StudentService extends IService<Student> {

    /**
     * @param username
     * @param password
     * @return Student
     * 学生登录接口
     */
    Student studentLogin(String username, String password);
}
