package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.ClassRoom;
import com.aliwo.entity.TeachbuildInfo;
import com.aliwo.service.ClassRoomService;
import com.aliwo.service.TeachBuildInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * 查询所有教学楼
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ServerResponse queryallTeachbuilding() {
        List<TeachbuildInfo> list = teachBuildInfoService.list();
        if (list == null || list.size() ==0 ){
            return ServerResponse.ofError("查询所有服务失败，请检查!");
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
        return ServerResponse.ofSuccess(list);
    }
}
