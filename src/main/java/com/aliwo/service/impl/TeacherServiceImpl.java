package com.aliwo.service.impl;

import com.aliwo.dao.TeacherDao;
import com.aliwo.entity.Teacher;
import com.aliwo.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * package_name:com.aliwo.service.impl
 *
 * @author:徐亚远 Date:2021/2/2 12:51
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    /**
     * @param username
     * @param password
     * @return Teacher
     * 讲师登录接口
     */
    @Override
    public Teacher teacherLogin(String username, String password) {
        return teacherDao.teacherLogin(username, password);
    }

    /**
     * @param teacher
     * @return Teacher
     * 查询账户是否存在
     */
    @Override
    public Teacher selectOne(Teacher teacher) {
        return teacherDao.selectOne(teacher);
    }
}
