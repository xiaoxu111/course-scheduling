package com.aliwo.entity.request;

import lombok.Data;

/**
 * package_name:com.aliwo.entity.request
 *
 * @author:xuyy19 Date:2021/1/24 18:59
 * 项目名:course-scheduling-system
 * Description:TODO
 * Version: 1.0
 **/
@Data
public class ClassTaskDTO {

    /**
     * 学期
     */
    private String semester;

    /**
     * 年级
     */
    private String gradeNo;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 讲师编号
     */
    private String teacherNo;

    /**
     * 讲师名字
     */
    private String realname;

    /**
     * 课程属性
     */
    private String courseAttr;

    /**
     * 上课人数
     */
    private Integer studentNum;

    /**
     * 周学时
     */
    private Integer weeksNumber;

    /**
     * 周数
     */
    private Integer weeksSum;

    /**
     * 是否固定上课时间
     */
    private String isFix;

    /**
     * 固定时间的话时间是什么时候
     */
    private String classTime;
}
