package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 18:55
 * 项目名:course-scheduling-system
 * Description:添加班级VO
 * Version: 1.0
 **/
@Data
public class ClassAddVO {
    /**
     * 讲师id
     */
    private Integer id;

    /**
     * 年级编号
     */
    private String gradeNo;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 班级名
     */
    private String className;

    /**
     * 班级人数
     */
    private Integer num;

    /**
     * 真实名
     */
    private String realname;
}
