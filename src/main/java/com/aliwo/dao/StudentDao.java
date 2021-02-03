package com.aliwo.dao;

import com.aliwo.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * package_name:com.aliwo.dao
 *
 * @author:徐亚远 Date:2021/2/2 14:18
 * 项目名:course-scheduling
 * Description:学生接口
 * Version: 1.0
 **/
public interface StudentDao extends BaseMapper<Student> {

    /**
     * @param username
     * @param password
     * @return Student
     * 学生登录接口
     */

    @Select("SELECT id, username, password, realname FROM yc_student WHERE student_no=#{account} AND " +
            "password=#{password}" +
            "        UNION" +
            "        SELECT id, username, password, realname FROM yc_student WHERE username=#{account} AND password=#{password}" +
            "        UNION" +
            "        SELECT id, username, password, realname FROM yc_student WHERE realname=#{account} AND password=#{password}")
    Student studentLogin(@Param(value = "account") String username, @Param(value = "password") String password);
}
