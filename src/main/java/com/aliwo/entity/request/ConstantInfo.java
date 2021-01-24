package com.aliwo.entity.request;

/**
 * @author:xuyy19 Date:2021/1/24 19:00
 * 项目名:course-scheduling-system
 * Description: 定义属性,方便切割编码的时候选择
 * Version: 1.0
 **/

public class ConstantInfo {

    /**
     * 是否是固定时间 1位
     */
    public static final String IS_FIX = "isFix";

    /**
     * 年级编号 2位
     */
    public static final String GRADE_NO = "grade_no";

    /**
     * 班级编号
     */
    public static final String CLASS_NO = "class_no";

    /**
     * 教师编号 位
     */
    public static final String TEACHER_NO = "teacher_no";

    /**
     * 课程编号 位
     */
    public static final String COURSE_NO = "course_no";

    /**
     * 课程属性编号 1位
     */
    public static final String COURSE_ATTR = "courseAttr";

    /**
     * 教室编号 6位
     */
    public static final String CLASSROOM_NO = "classroom_no";

    /**
     * 上课时间2位
     */
    public static final String CLASS_TIME = "class_time";

    /**
     * 开课学期
     */
    public static final String SEMESTER = "semester";

    /**
     * 默认课程的编码
     */
    public static final String DEAULT_CLASS_TIME = "00";

    /**
     * 设置各种类型的课程的适应度(码值) 语数英
     */
    public static final String MAIN_COURSE = "01";

    /**
     * 物理化学生物 政治历史地理
     */
    public static final String SECONDARY_COURSE = "02";

    /**
     * 实验课
     */
    public static final String EXPERIMENT_COURSE = "02";

    // 体育课
    public static final String PHYSICAL_COURSE = "04";

    // 音乐课
    public static final String MUSIC_COURSE = "05";

    // 舞蹈课
    public static final String DANCE_COURSE = "06";

    // 信息技术
    public static final String TECHNOLOGY_COURSE = "07";

    // 设置遗传代数
    public static final int GENERATION = 50;

}
