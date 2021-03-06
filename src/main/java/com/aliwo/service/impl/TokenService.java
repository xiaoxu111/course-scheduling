package com.aliwo.service.impl;

import com.aliwo.entity.Admin;
import com.aliwo.entity.Student;
import com.aliwo.entity.Teacher;
import com.aliwo.util.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * package_name:com.aliwo.service.impl
 *
 * @author:xuyy19 Date:2021/1/24 14:59
 * 项目名:course-scheduling
 * Description:获取token的服务
 * Version: 1.0
 **/
@Service
public class TokenService {
    /**
     * 验证管理员
     *
     * @param admin
     * @return
     */
    public String getToken(Admin admin) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 500;
        Date end = new Date(currentTime);
        String dateToString = DateUtil.DateToString(end, "yyyy-mm-dd HH:mm:ss");
        String token = "";
        token = JWT.create().withAudience(admin.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(admin.getPassword()));
        return token;
    }

    /**
     * 验证讲师
     *
     * @param teacher
     * @return
     */
    public String getToken(Teacher teacher) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 500;
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(teacher.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(teacher.getPassword()));
        return token;
    }

    /**
     * 验证管理员
     *
     * @param student
     * @return
     */
    public String getToken(Student student) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 500;
        Date end = new Date(currentTime);
        String dateToString = DateUtil.DateToString(end, "yyyy-mm-dd HH:mm:ss");
        String token = "";
        token = JWT.create().withAudience(student.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(student.getPassword()));
        return token;
    }
}
