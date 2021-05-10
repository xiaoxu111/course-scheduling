package com.aliwo.dao;

import com.aliwo.entity.CoursePlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * package_name:com.aliwo.dao
 *
 * @author:xuyy19 Date:2021/2/6 13:19
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface CoursePlanDao extends BaseMapper<CoursePlan> {


    // 插入课程计划
    @Insert("insert into yc_course_plan(grade_no, class_no, course_no, teacher_no, class_room_no, class_time, " +
            "semester) values(#{grade_no}, #{class_no}, #{course_no}, #{teacher_no}, #{class_room_no}, #{class_time}," +
            " " +
            "#{semester})")
    void insertCoursePlan(
            @Param("grade_no") String grade_no, @Param("class_no") String class_no,
            @Param("course_no") String course_no, @Param("teacher_no") String teacher_no,
            @Param("class_room_no") String class_room_no, @Param("class_time") String class_time,
            @Param("semester") String semester
    );

    @Update("truncate yc_course_plan")
    void deleteAllPlan();

}
