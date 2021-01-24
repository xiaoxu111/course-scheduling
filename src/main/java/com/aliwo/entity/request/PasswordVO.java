package com.aliwo.entity.request;

import lombok.Data;

/**
 * package_name:com.aliwo.entity.request
 *
 * @author:徐亚远 Date:2021/1/24 19:41
 * 项目名:course-scheduling-system
 * Description:TODO
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
