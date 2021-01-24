package com.aliwo.entity.response;

import com.aliwo.entity.CourseInfo;
import com.aliwo.entity.CoursePlan;
import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 18:51
 * 项目名:course-scheduling-system
 * Description:排课Vo
 * Version: 1.0
 **/
@Data
public class CoursePlanVo extends CoursePlan {

    /**
     * 教师名
     */
    private String teacher;

    /**
     * 课程信息表
     */
    private CourseInfo courseInfo;
}
