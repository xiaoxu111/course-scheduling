package com.aliwo.service.impl;

import com.aliwo.dao.AdminDao;
import com.aliwo.entity.Admin;
import com.aliwo.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * package_name:com.aliwo.service.impl
 *
 * @author:徐亚远 Date:2021/1/24 13:54
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Autowired
    private AdminDao adminDao;

    /**
     * @param username
     * @param password
     * @return Admin
     * 管理员登录接口
     */
    @Override
    public Admin adminLogin(String username, String password) {
        return adminDao.adminLogin(username, password);
    }
}
