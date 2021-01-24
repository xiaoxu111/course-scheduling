package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 19:40
 * 项目名:course-scheduling-system
 * Description:TODO
 * Version: 1.0
 **/
@Data
public class OnlineCourseAddVO {

    /**
     * 网课名
     */
    private String onlineName;

    /**
     * 课程简介
     */
    private String description;

    /**
     * 课程封面
     */
    private String cover;

    /**
     * 网课类别id
     */
    private Integer onlineCategoryId;

    /**
     * 网课类别名称
     */
    private String onlineCategoryName;

    /**
     * 操作的用户类型，1为管理员，2为讲师
     */
    private Integer fromUserType;

    /**
     * 操作的用户id
     */
    private Integer fromUserId;

    /**
     * 操作用户名
     */
    private String fromUserName;
}
