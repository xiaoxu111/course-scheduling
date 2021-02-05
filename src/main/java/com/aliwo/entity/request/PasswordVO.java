package com.aliwo.entity.request;

import lombok.Data;

/**
 * package_name:com.aliwo.entity.request
 *
 * @author:xuyy19 Date:2021/1/24 19:41
 * 项目名:course-scheduling-system
 * Description:修改密码时封装VO
 * Version: 1.0
 **/
@Data
public class PasswordVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 旧密码
     */
    private String oldPass;

    /**
     * 新密码
     */
    private String newPass;

    /**
     * 重置密码
     */
    private String rePass;
}
