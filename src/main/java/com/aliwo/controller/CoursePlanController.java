package com.aliwo.controller;

import com.alibaba.fastjson.JSON;
import com.aliwo.common.ServerResponse;
import com.aliwo.entity.CourseInfo;
import com.aliwo.entity.CoursePlan;
import com.aliwo.entity.Teacher;
import com.aliwo.entity.response.CoursePlanVo;
import com.aliwo.service.CourseInfoService;
import com.aliwo.service.CoursePlanService;
import com.aliwo.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy19 Date:2021/2/6 13:29
 * 项目名:course-scheduling
 * Description:排课Controller
 * Version: 1.0
 **/
@RestController
public class CoursePlanController {
    @Autowired
    private CoursePlanService coursePlanService;

    @Autowired
    private CourseInfoService courseInfoService;

    @Autowired
    private TeacherService teacherService;
    /**
     * 根据班级查询课程表
     * @param classNo
     * @return
     */
    @RequestMapping(value = "/courseplan/{classno}", method = RequestMethod.GET)
    public ServerResponse queryCoursePlanByClassNo(@PathVariable("classno") String classNo) {
        QueryWrapper<CoursePlan> wrapper = new QueryWrapper<CoursePlan>().eq("class_no", classNo).orderByAsc("class_time");
        List<CoursePlan> coursePlanList = coursePlanService.list(wrapper);

        if (coursePlanList == null || coursePlanList.size() ==0) {
            return ServerResponse.ofError("排课计划为空！！！");
        }

        List<CoursePlanVo> coursePlanVos = new LinkedList<>();
        coursePlanList.forEach(v->{
            CoursePlanVo coursePlanVo = JSON.parseObject(JSON.toJSONString(v), CoursePlanVo.class);
            coursePlanVo.setCourseInfo(courseInfoService.getOne(new QueryWrapper<CourseInfo>().eq("course_no",v.getCourseNo())));
            coursePlanVo.setTeacher(teacherService.getOne(new QueryWrapper<Teacher>().eq("teacher_no",v.getTeacherNo())));
            coursePlanVos.add(coursePlanVo);
        });
        return ServerResponse.ofSuccess(coursePlanVos);
    }


}
