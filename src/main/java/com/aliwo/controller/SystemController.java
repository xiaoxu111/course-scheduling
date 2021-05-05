package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.dao.StudentDao;
import com.aliwo.dao.TeacherDao;
import com.aliwo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuyayuan
 * @data 2021年05月05日
 */
@RestController
public class SystemController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private ClassTaskService classTaskService;
    @Autowired
    private TeachBuildInfoService teachBuildInfoService;
    @Autowired
    private DocService docService;
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private OnlineCourseService onlineCourseService;
    @Autowired
    private TeacherDao teacherDao;


    @RequestMapping(value = "/systemdata", method = RequestMethod.POST)
    public ServerResponse systemData() {
        Map<String, Object> map = new HashMap<>();

        // 讲师人数
        int teachers = teacherService.count();
        // 学生人数
        int students = studentService.count();
        // 教材数量
        int courses = courseInfoService.count();
        // 班级数量
        int classes = classInfoService.count();
        // 教学楼数量
        int teachbuilds = teachBuildInfoService.count();
        // 教室数量
        int classrooms = classRoomService.count();
        // 当前课程任务数量
        int classtasks = classTaskService.count();
        // 学习文档数
        int docs = docService.count();
        // 网课数量
        int onlineCourse = onlineCourseService.count();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date d = cal.getTime();
        // 日期格式化
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        //获取昨天日期
        String yesterday = sp.format(d);


        // 昨日学生注册人数
        int studentReg = studentDao.studentReg(yesterday);
        // 昨日注册讲师
        int teacherReg = teacherDao.teacherReg(yesterday);
        map.put("teachers", teachers);
        map.put("students", students);
        map.put("courses", courses);
        map.put("classes", classes);
        map.put("teachbuilds", teachbuilds);
        map.put("classtasks", classtasks);
        map.put("docs", docs);
        map.put("classrooms", classrooms);
        map.put("studentReg", studentReg);
        map.put("onlineCourse", onlineCourse);
        map.put("teacherReg", teacherReg);
        return ServerResponse.ofSuccess(map);
    }
}
