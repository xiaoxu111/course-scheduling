package com.aliwo.service.impl;

import com.aliwo.dao.StudentDao;
import com.aliwo.entity.Student;
import com.aliwo.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * package_name:com.aliwo.service.impl
 *
 * @author:徐亚远 Date:2021/2/2 14:23
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Autowired
    private StudentDao studentDao;
    /**
     * @param username
     * @param password
     * @return Student
     * 学生登录接口
     */
    @Override
    public Student studentLogin(String username, String password) {
        return studentDao.studentLogin(username,password );
    }
}
