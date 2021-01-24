package com.aliwo.service;

import com.aliwo.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * @author:徐亚远 Date:2021/1/24 13:52
 * 项目名:course-scheduling
 * Description:管理员服务接口
 * Version: 1.0
 **/
public interface AdminService extends IService<Admin> {

    /**
     * @param username
     * @param password
     * @return Admin
     * 管理员登录接口
     */
    Admin adminLogin(@Param("username") String username, @Param("password") String password);


}
