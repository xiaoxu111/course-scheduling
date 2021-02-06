package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.ClassInfo;
import com.aliwo.service.ClassInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 根据年级查询所有班级 个人中心加入班级的时候调用
     * @param grade
     * @return
     */
    @GetMapping("/by-grade-for-class/{grade}")
    public ServerResponse queryClass(@PathVariable("grade") String grade) {
        // remark 对应yc_class_info表中的remark字段,记录的是年级信息
        QueryWrapper<ClassInfo> wrapper = new QueryWrapper<ClassInfo>().eq("remark", grade);
        List<ClassInfo> classInfoList = classInfoService.list(wrapper);
        return ServerResponse.ofSuccess(classInfoList);
    }
}
