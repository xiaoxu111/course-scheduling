package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 19:51
 * 项目名:course-scheduling-system
 * Description:添加网课，上传视频时操作用户的信息，类型，
 * Version: 1.0
 **/
@Data
public class UserInfoVO {

    /**
     * 课程的id
     */
    private Integer courseId;

    /**
     * 用户类型
     */
    private Integer UserType;

    /**
     * 操作者的id
     */
    private Integer id;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 设置视频的编号，比如3-1
     */
    private String videoNo;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 视频封面
     */
    private String cover;
}
