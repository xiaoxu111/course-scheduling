package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.dao.ClassInfoDao;
import com.aliwo.entity.ClassInfo;
import com.aliwo.entity.Student;
import com.aliwo.entity.Teacher;
import com.aliwo.entity.request.ClassAddVO;
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
            if (page > 1){
                page = (page - 1) * 10;
            }
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


    /**
     * 班级管理 所有班级,管理员根据班级ID删除班级信息
     *
     * @return ServerResponse
     */
    @RequestMapping(value = "/deleteclass/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteClassById(@PathVariable Integer id) {
        if (StringUtils.isEmpty(id.toString())) {
            return ServerResponse.ofError("删除服务失败(id:) ！！！" + id);
        }
        ClassInfo classInfo = classInfoService.getById(id);
        if (classInfo != null && classInfo.getNum() != 0) {
            return ServerResponse.ofError("所在班级已经分配学生不能删除该班级！！！");
        }
        boolean b = classInfoService.removeById(id);
        if (b) {
            return ServerResponse.ofSuccess("删除成功！");
        }
        return ServerResponse.ofError("删除失败！");
    }

    /**
     * 添加班级
     *
     * @param classAddVO
     * @return ServerResponse
     */
    @RequestMapping(value = "/addclassinfo", method = RequestMethod.POST)
    public ServerResponse addClass(@RequestBody ClassAddVO classAddVO) {
        if (null == classAddVO){
            return ServerResponse.ofError("添加班级服务失败！！！");
        }
        if (classAddVO.getId()==null){
            return ServerResponse.ofError("班级id非空！！！");
        }
        if (Strings.isEmpty(classAddVO.getGradeNo())){
            return ServerResponse.ofError("年级编号非空！！！");
        }
        if (Strings.isEmpty(classAddVO.getClassNo())){
            return ServerResponse.ofError("班级编号非空！！！");
        }
        if (Strings.isEmpty(classAddVO.getClassName())){
            return ServerResponse.ofError("班级名称非空！！！");
        }
        ClassInfo c = new ClassInfo();
        c.setRemark(classAddVO.getGradeNo());
        c.setClassNo(classAddVO.getClassNo());
        c.setClassName(classAddVO.getClassName());
        c.setTeacherId(classAddVO.getId());
        c.setNum(classAddVO.getNum());
        boolean b = classInfoService.save(c);
        if (b) {
            return ServerResponse.ofSuccess("添加班级成功");
        }
        return ServerResponse.ofError("添加班级失败");
    }

}
