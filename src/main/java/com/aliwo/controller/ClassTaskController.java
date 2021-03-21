package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.ClassTask;
import com.aliwo.entity.request.ClassTaskDTO;
import com.aliwo.service.ClassTaskService;
import com.aliwo.util.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * package_name:com.aliwo.controller
 *
 * @author:徐亚远 Date:2021/2/28 18:04
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@RestController
public class ClassTaskController {

    private static final Logger LOG = LoggerFactory.getLogger(ClassTaskController.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");

    @Autowired
    private ClassTaskService classTaskService;

    /**
     * 查询开课任务
     */
    @GetMapping("/classtask/{page}/{semester}")
    public ServerResponse queryClassTask(@PathVariable("page") Integer page,
                                         @PathVariable("semester") String semester,
                                         @RequestParam(defaultValue = "10") Integer limit) {
        Page<ClassTask> pages = new Page<>(page, limit);
        // 课程创建时间降序
        QueryWrapper<ClassTask> wrapper = new QueryWrapper<ClassTask>().eq("semester", semester).orderByDesc
                ("create_time");
        IPage<ClassTask> ipage = classTaskService.page(pages, wrapper);
        if (ipage != null) {
            List<ClassTask> classTaskList = ipage.getRecords();
            if (classTaskList != null && classTaskList.size() > 0){
               for (ClassTask classTask : classTaskList){
                   if ("1".equals(classTask.getIsFix())){
                        classTask.setIsFix("固定");
                   }
                   if ("2".equals(classTask.getIsFix())){
                       classTask.setIsFix("不固定");
                   }
               }
            }
            return ServerResponse.ofSuccess(ipage);
        }
        return ServerResponse.ofError("查询开课任务失败！");
    }

    /**
     * 获得学期集合,如：
     * 2019-2020-1
     * 2019-2020-2
     *
     * @return
     */
    @GetMapping("/semester")
    public ServerResponse queryAllSemester() {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.select("semester");
        wrapper.groupBy("semester");
        List<ClassTask> list = classTaskService.list(wrapper);
        Set set = new HashSet();

        for (ClassTask c : list) {
            set.add(c.getSemester());
        }

        return ServerResponse.ofSuccess(set);
    }


    /**
     * 手动添加课程任务
     *
     * @param c
     * @return
     */
    @RequestMapping(value = "/addclasstask",method = RequestMethod.POST)
    public ServerResponse addClassTask(@RequestBody() ClassTaskDTO c) {
        LOG.info("添加排课任务数据实体：" + c);
        ClassTask classTask = new ClassTask();
        ServerResponse x = checkAddClassTaskParam(c, classTask);
        if (x != null) return x;
        boolean b = classTaskService.save(classTask);

        if (b) {
            return ServerResponse.ofSuccess("添加课程任务成功");
        }
        return ServerResponse.ofError("添加课程任务失败");
    }

    /**
     * @param c
     * @param classTask
     * @return
     *  检查添加是否合法参数
     */
    private ServerResponse checkAddClassTaskParam(@RequestBody() ClassTaskDTO c, ClassTask classTask) {
        if (Strings.isEmpty(c.getSemester())){
            return ServerResponse.ofError("学期必填，非空！！！");
        }
        classTask.setSemester(c.getSemester());
        if (Strings.isEmpty(c.getGradeNo())){
            return ServerResponse.ofError("年级必填，非空！！！");
        }
        classTask.setGradeNo(c.getGradeNo());
        if (Strings.isEmpty(c.getClassNo())){
            return ServerResponse.ofError("班级必填，非空！！！");
        }
        classTask.setClassNo(c.getClassNo());
        if (Strings.isEmpty(c.getCourseNo())){
            return ServerResponse.ofError("课程编号必填，非空！！！");
        }
        classTask.setCourseNo(c.getCourseNo());
        if (Strings.isEmpty(c.getCourseName())){
            return ServerResponse.ofError("课程名必填，非空！！！");
        }
        classTask.setCourseName(c.getCourseName());
        if (Strings.isEmpty(c.getTeacherNo())){
            return ServerResponse.ofError("讲师编号必填，非空！！！");
        }
        classTask.setTeacherNo(c.getTeacherNo());
        if (Strings.isEmpty(c.getRealname())){
            return ServerResponse.ofError("讲师名字必填，非空！！！");
        }
        classTask.setRealname(c.getRealname());
        if (Strings.isEmpty(c.getCourseAttr())){
            return ServerResponse.ofError("课程属性必填，非空！！！");
        }
        classTask.setCourseAttr(c.getCourseAttr());
        if (Strings.isEmpty(String.valueOf(c.getStudentNum()))){
            return ServerResponse.ofError("学生人数必填，非空！！！");
        }
        classTask.setStudentNum(c.getStudentNum());
        if (Strings.isEmpty(String.valueOf(c.getWeeksNumber()))){
            return ServerResponse.ofError("周学时必填，非空！！！");
        }
        classTask.setWeeksNumber(c.getWeeksNumber());
        if (Strings.isEmpty(String.valueOf(c.getWeeksSum()))){
            return ServerResponse.ofError("周数必填，非空！！！");
        }
        classTask.setWeeksSum(c.getWeeksSum());
        if (Strings.isEmpty(String.valueOf(c.getIsFix()))){
            return ServerResponse.ofError("是否固定必填，非空！！！");
        }
        classTask.setIsFix(c.getIsFix());
        classTask.setClassTime(c.getClassTime());
        return null;
    }

    /**
     * 删除开课任务
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteclasstask/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteClassTask(@PathVariable("id") Integer id) {

        boolean b = classTaskService.removeById(id);
        if (b) {
            return ServerResponse.ofSuccess("删除成功");
        }
        return ServerResponse.ofError("删除失败");
    }
}
