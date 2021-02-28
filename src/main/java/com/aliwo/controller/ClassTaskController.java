package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.ClassTask;
import com.aliwo.service.ClassTaskService;
import com.aliwo.util.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

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
        QueryWrapper<ClassTask> wrapper = new QueryWrapper<ClassTask>().eq("semester", semester);
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
}
