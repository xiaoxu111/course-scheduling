package com.aliwo.dao;

import com.aliwo.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * package_name:com.aliwo.dao
 *
 * @author:徐亚远 Date:2021/2/2 12:42
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface TeacherDao extends BaseMapper<Teacher> {
    /**
     * @param accont
     * @param password
     * @return Teacher
     * 可以根据(一:)教师编号和密码或者(二:)用户名和密码或者(三:)真实姓名和密码进行登录(三种登录方式)
     */
    @Select("SELECT id, password, username, realname FROM yc_teacher WHERE teacher_no=#{account} AND " +
            "password=#{password}" +
            "        UNION" +
            "        SELECT id, password, username, realname  FROM yc_teacher WHERE username=#{account} AND " +
            "password=#{password}" +
            "        UNION" +
            "        SELECT id, password, username, realname  FROM yc_teacher WHERE realname=#{account} AND " +
            "password=#{password}")
    Teacher teacherLogin(@Param("account") String accont, @Param("password") String password);

    /**
     * @param teacher
     * @return Teacher
     * 查询账户是否存在
     */
    Teacher selectOne(Teacher teacher);

}
