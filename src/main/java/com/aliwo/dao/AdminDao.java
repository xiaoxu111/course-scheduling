package com.aliwo.dao;

import com.aliwo.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author:xuyy19 Date:2021/1/24 13:31
 * 项目名:course-scheduling
 * Description:管理员登录接口
 * Version: 1.0
 **/
public interface AdminDao extends BaseMapper<Admin> {

    /**
     * @param account
     * @param password
     * @return Admin
     * 可以根据(一:)管理员编号和密码或者(二:)用户名和密码或者(三:)真实姓名和密码进行登录(三种登录方式)
     */
    @Select(
            "SELECT * FROM yc_admin WHERE adminer_no = #{account} AND password = #{password}"
            + "   UNION" +
            "           SELECT * FROM tb_admin WHERE username=#{account} AND password=#{password}"
            + "   UNION" +
            "           SELECT * FROM tb_admin WHERE realname=#{account} AND password=#{password}"
    )
    Admin adminLogin(@Param("account") String account, @Param("password") String password);
}
