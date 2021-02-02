package com.aliwo.service;

import com.aliwo.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * package_name:com.aliwo.service
 *
 * @author:徐亚远 Date:2021/2/2 12:48
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface TeacherService extends IService<Teacher> {

    /**
     * @param username
     * @param password
     * @return Teacher
     *  讲师登录接口
     */
    Teacher teacherLogin(String username, String password);

    /**
     * @param teacher
     * @return Teacher
     * 查询账户是否存在
     */
    Teacher selectOne(Teacher teacher);

}
