package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.ClassRoom;
import com.aliwo.entity.CoursePlan;
import com.aliwo.service.ClassRoomService;
import com.aliwo.service.CoursePlanService;
import com.aliwo.util.NoForNameUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy19 Date:2021/2/6 12:57
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@RestController
@RequestMapping("/classroom")
public class ClassRoomController {
    private static final Logger LOG = LoggerFactory.getLogger(ClassRoomController.class);
    @Autowired
    private ClassRoomService classRoomService;
    @Autowired
    private CoursePlanService coursePlanService;

    /**
     * @param teachbuildNo
     * @return ServerResponse
     * 根据教学楼编号查询空教室
     */
    @RequestMapping(value = "/empty/{page}/{teachbuildno}", method = RequestMethod.GET)
    public ServerResponse getEmptyClassroom(@PathVariable("teachbuildno") String teachbuildNo,
                       @PathVariable(value = "page") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        // 首先查询该教学楼下面的所有教室
        QueryWrapper<ClassRoom> wrapper1 = new QueryWrapper();
        wrapper1.eq("teachbuild_no", teachbuildNo);
        // 查询出指定教学楼下的所有教室
        List<ClassRoom> allClassroom = classRoomService.list(wrapper1);
        if (allClassroom == null || allClassroom.size() ==0){
            return ServerResponse.ofError("【"+NoForNameUtil.getTeachBuildName(teachbuildNo)+"没有相应的教室信息！！！"+"】");
        }
        // 查询出所有的排课信息
        List<CoursePlan> coursePlanList = coursePlanService.list();
        if (coursePlanList == null || coursePlanList.size() ==0){
            return ServerResponse.ofError("查询排课信息服务失败，请联系管理员！！！");
        }
        // 得到已经使用了的教室编号  教室编号编码规则：教学楼编号+教室编号组成
        Set<String> usedClaassroom = new HashSet<>();
        for (int i = 0 , coursePlansize = coursePlanList.size(); i < coursePlansize; i++) {
            // 截取占用的教室所属编号前两位，即教学楼编号 判断教学楼编号是true则教室已经使用。
            if (teachbuildNo.equals(coursePlanList.get(i).getClassRoomNo().substring(0, 2))) {
                usedClaassroom.add(coursePlanList.get(i).getClassRoomNo());
            }
        }
        List<ClassRoom> usedRoomList = new ArrayList<>();
        if (usedClaassroom != null && usedClaassroom.size() != 0){
            QueryWrapper<ClassRoom> wrapper2 = new QueryWrapper();
            wrapper2.in("class_room_no", usedClaassroom);
            wrapper2.orderByAsc("class_room_no");
            // 查询出已经使用的教室
            usedRoomList = classRoomService.list(wrapper2);
        }
        // 如果该教学楼下没有被使用的教室则返回该教学楼下所有的教室信息
        if (usedRoomList == null || usedClaassroom.size() ==0){
            return ServerResponse.ofSuccess(allClassroom);
        }
        // 得到空教室  所有的教室与被使用的教室取差集
        Set<ClassRoom> newSet = getSub(allClassroom, usedRoomList);
        // 返回空教室信息
        return ServerResponse.ofSuccess(newSet);
    }

    /**
     * @param teachbuildNo
     * @return ServerResponse
     * 根据教学楼编号查询空教室
     */
    @RequestMapping(value = "/empty/{teachbuildno}", method = RequestMethod.GET)
    public ServerResponse getEmptyClassroom(@PathVariable("teachbuildno") String teachbuildNo) {
        Map<String, Object> map = new HashMap();
        // 首先查询该教学楼下面的所有教室
        QueryWrapper<ClassRoom> wrapper1 = new QueryWrapper();
        wrapper1.eq("teachbuild_no", teachbuildNo);
        // 查询出指定教学楼下的所有教室
        List<ClassRoom> allClassroom = classRoomService.list(wrapper1);
        if (allClassroom == null || allClassroom.size() ==0){
            return ServerResponse.ofError("【"+NoForNameUtil.getTeachBuildName(teachbuildNo)+"没有相应的教室信息！！！"+"】");
        }
        // 查询出所有的排课信息
        List<CoursePlan> coursePlanList = coursePlanService.list();
        if (coursePlanList == null || coursePlanList.size() ==0){
            return ServerResponse.ofError("查询排课信息服务失败，请联系管理员！！！");
        }
        // 得到已经使用了的教室编号  教室编号编码规则：教学楼编号+教室编号组成
        Set<String> usedClaassroom = new HashSet<>();
        for (int i = 0 , coursePlansize = coursePlanList.size(); i < coursePlansize; i++) {
            // 截取占用的教室所属编号前两位，即教学楼编号 判断教学楼编号是true则教室已经使用。
            if (teachbuildNo.equals(coursePlanList.get(i).getClassRoomNo().substring(0, 2))) {
                usedClaassroom.add(coursePlanList.get(i).getClassRoomNo());
            }
        }
        List<ClassRoom> usedRoomList = new ArrayList<>();
        if (usedClaassroom != null && usedClaassroom.size() != 0){
            QueryWrapper<ClassRoom> wrapper2 = new QueryWrapper();
            wrapper2.in("class_room_no", usedClaassroom);
            wrapper2.orderByAsc("class_room_no");
            // 查询出已经使用的教室
            usedRoomList = classRoomService.list(wrapper2);
        }
        // 如果该教学楼下没有被使用的教室则返回该教学楼下所有的教室信息
        if (usedRoomList == null || usedClaassroom.size() ==0){
            LOG.info(teachbuildNo+"空教室的数量:"+allClassroom.size()+"教室都是空的!!");
            map.put("records", allClassroom);
            map.put("total", allClassroom.size());
            return ServerResponse.ofSuccess(map);
        }
        // 得到空教室  所有的教室与被使用的教室取差集
        Set<ClassRoom> newSet = getSub(allClassroom, usedRoomList);
        LOG.info(teachbuildNo+"空教室的数量:"+newSet.size());
        map.put("records", newSet);
        map.put("total", newSet.size());
        // 返回空教室信息
        return ServerResponse.ofSuccess(map);
    }


    /**
     * 集合取差
     * @param list1
     * @param list2
     * @return
     *  返回空教室信息
     */
    private Set<ClassRoom> getSub(List<ClassRoom> list1, List<ClassRoom> list2) {
        Set<ClassRoom> newSet = new HashSet<>();
        for (int i = 0 ,list1Size = list1.size(); i <list1Size; i++) {
            // 遍历集合2，判断集合1中是否包含集合2中元素，若包含，则把这个共同元素加入新集合中
            for (int j = 0, list2Size = list2.size(); j <list2Size; j++) {
                if (!(list1.get(i).equals(list2.get(j)) || list1.get(i) == list2.get(j))) {
                    newSet.add(list1.get(i));
                }
            }
        }
        return newSet;
    }
}
