package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.ClassRoom;
import com.aliwo.entity.TeachbuildInfo;
import com.aliwo.service.ClassRoomService;
import com.aliwo.service.TeachBuildInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy19 Date:2021/2/6 12:18
 * 项目名:course-scheduling
 * Description:教学楼信息Controller
 * Version: 1.0
 **/
@RestController
@RequestMapping("/teachbuildinfo")
public class TeachBuildInfoController {
    @Autowired
    private TeachBuildInfoService teachBuildInfoService;

    @Autowired
    private ClassRoomService classRoomService;

    /**
     * 查询所有教学楼,如果教学楼下没有教室信息则不显示该教学楼,否则显示教学楼信息
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse queryallTeachbuilding() {
        List<TeachbuildInfo> list = teachBuildInfoService.list();
        if (list == null || list.size() ==0 ){
            return ServerResponse.ofError("查询教学楼服务失败，请检查！！！");
        }
        // 首先查询该教学楼下面的所有教室
        for (int i=0; i<list.size(); i++) {
            QueryWrapper<ClassRoom> wrapper1 = new QueryWrapper();
            List<ClassRoom> allClassroom = new ArrayList<>();
            wrapper1.eq("teachbuild_no", list.get(i).getTeachBuildNo());
            // 查询出指定教学楼下的所有教室
            allClassroom = classRoomService.list(wrapper1);
            if (allClassroom == null || allClassroom.size() ==0){
               list.remove(i);
               i--;
            }
        }
        return ServerResponse.ofSuccess(0,"请先选择要教学楼信息 ！！！",list);
    }

    /**
     * 分页查询所有教学楼
     * @return
     */
    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public ServerResponse queryTeachbuilding(@PathVariable("page") Integer page,
                                             @RequestParam(defaultValue = "10") Integer limit) {
        Page<TeachbuildInfo> pages = new Page<>(page, limit);
        QueryWrapper<TeachbuildInfo> wrapper = new QueryWrapper<TeachbuildInfo>().orderByDesc("update_time");
        IPage<TeachbuildInfo> ipage = teachBuildInfoService.page(pages, wrapper);
        if (ipage != null) {
            return ServerResponse.ofSuccess(ipage);
        }
        return ServerResponse.ofError("查询失败");
    }
}
