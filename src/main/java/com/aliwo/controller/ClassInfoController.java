package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.dao.ClassInfoDao;
import com.aliwo.entity.ClassInfo;
import com.aliwo.entity.Student;
import com.aliwo.entity.Teacher;
import com.aliwo.entity.response.ClassInfoVo;
import com.aliwo.service.ClassInfoService;
import com.aliwo.service.StudentService;
import com.aliwo.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy19 Date:2021/2/4 15:51
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@RestController
public class ClassInfoController {
    @Autowired
    private ClassInfoService classInfoService;

    @Autowired
    private ClassInfoDao classInfoDao;

    @Autowired
    private StudentService studentService;

 @Autowired
    private TeacherService teacherService;
    /**
     * 根据年级查询所有班级 个人中心加入班级的时候调用
     * @param grade
     * @return
     */
    @GetMapping("/by-grade-for-class/{grade}")
    public ServerResponse queryClass(@PathVariable("grade") String grade) {
        // remark 对应yc_class_info表中的remark字段,记录的是年级信息
        /*if (StringUtils.isNotEmpty(grade)){
            switch (grade){
                case "高一":
                    grade="01";
                    break;
                case "高二":
                    grade="02";
                    break;
                case "高三":
                    grade="03";
                    break;
                default:
                    grade="04";
                    break;
            }
        }*/
        QueryWrapper<ClassInfo> wrapper = new QueryWrapper<ClassInfo>().eq("remark", grade);
        List<ClassInfo> classInfoList = classInfoService.list(wrapper);
        return ServerResponse.ofSuccess(classInfoList);
    }


    /**
     * @param page
     * @param limit
     * @param gradeNo
     * @return ServerResponse
     * 查询班级信息带详细信息,  班级管理功能 所有班级
     */
    @GetMapping("/queryclassinfo/{page}")
    public ServerResponse queryClassInfos(@PathVariable("page") Integer page, @RequestParam(defaultValue = "10") Integer limit,
                                          @RequestParam(defaultValue = "") String gradeNo) {
        Map<String, Object> map = new HashMap();
        List<ClassInfoVo> classInfoVOS = null;
        if (StringUtils.isEmpty(gradeNo)) {
            // 分页查询所有班级信息
            classInfoVOS = classInfoDao.queryClassInfos(page, limit);
            int total = classInfoDao.count2();
            map.put("records", classInfoVOS);
            map.put("total", total);
            return ServerResponse.ofSuccess(0,"查询所有班级信息成功 ！！！",map);
        } else {
            // 班级管理 所有班级 根据年级查询班级信息 例如： 高二  查询出来的都是高二年级所在的班级信息
            classInfoVOS = classInfoDao.queryClassInfoByGradeNo(page, limit, gradeNo);
            int total = classInfoDao.count1(gradeNo);
            map.put("records", classInfoVOS);
            map.put("total", total);
            return ServerResponse.ofSuccess(0,"根据年级查询所有班级信息成功 ！！！",map);
        }
    }


    /**
     * 根据班级查询学生分页 学生管理所有学生 年级-班级  例如：高一  20年高三三班
     *
     * @param page
     * @param classNo
     * @param limit
     * @return
     */
    @GetMapping("/student-class/{page}/{classNo}")
    public ServerResponse queryStudentByClass(@PathVariable("page") Integer page,
                                              @PathVariable("classNo") String classNo,
                                              @RequestParam(defaultValue = "10") Integer limit) {
        if (StringUtils.isEmpty(classNo)) {
            return ServerResponse.ofError("根据年级查询学生服务错误！！！");
        }
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("student_no");
        wrapper.like(!StringUtils.isEmpty(classNo), "class_no", classNo);
        Page<Student> pages = new Page<>(page, limit);
        IPage<Student> iPage = studentService.page(pages, wrapper);
        if (null == iPage || 0 == iPage.getTotal()) {
            return ServerResponse.ofError("没有查询到学生信息");
        } else {
            return ServerResponse.ofSuccess(0, "查询成功 ！！！", iPage);
        }
    }


    /**
     * 班级管理 所有班级选项,编辑班级信息
     *
     * @param classInfoVO
     * @param id
     * @return
     */
    @PostMapping("/modifyClass/{id}")
    public ServerResponse modifyClass(@PathVariable("id") Integer id, @RequestBody ClassInfoVo classInfoVO) {

        if (null == classInfoVO) {
            return ServerResponse.ofError("更新班级服务错误！！！");
        }
        if (Strings.isEmpty(classInfoVO.getClassNo())) {
            return ServerResponse.ofError("请填写班级编号！！！");
        }
        if (Strings.isEmpty(classInfoVO.getClassName())) {
            return ServerResponse.ofError("请填写班级名称！！！");
        }
        if (Strings.isEmpty(classInfoVO.getRealname())) {
            return ServerResponse.ofError("请填写班主任姓名！！！");
        }
        QueryWrapper<ClassInfo> wrapper = new QueryWrapper<ClassInfo>().eq("id", id);
        ClassInfo classInfo = classInfoService.getOne(wrapper);
        if (null == classInfo) {
            return ServerResponse.ofError("没有查到班级信息根据班级id ！！！");
        }
        // 设置年级
        switch (classInfoVO.getGradeName()) {
            case "高一":
                classInfo.setRemark("01");
                break;
            case "高二":
                classInfo.setRemark("02");
                break;
            case "高三":
                classInfo.setRemark("03");
                break;
                default:
                    break;
        }
        // 设置班级编号
        classInfo.setClassNo(classInfoVO.getClassNo());
        // 设置班级名称
        classInfo.setClassName(classInfoVO.getClassName());
        // 设置班级人数
        classInfo.setNum(classInfoVO.getNum());
        Boolean flag = classInfoService.updateById(classInfo);
        if (!flag){
            return ServerResponse.ofError("更新班级信息失败");
        }
        // 讲师id
        Integer teacherId = classInfo.getTeacherId();
        if (teacherId ==null) {
            return ServerResponse.ofError("更新失败！！！");
        }
        Teacher teacher = teacherService.getById(teacherId);

        return ServerResponse.ofSuccess("修改成功");
    }

}
