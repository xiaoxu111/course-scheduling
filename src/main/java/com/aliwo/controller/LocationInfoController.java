package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.dao.LocationInfoDao;
import com.aliwo.entity.LocationInfo;
import com.aliwo.entity.request.LocationSetVO;
import com.aliwo.entity.response.LocationVO;
import com.aliwo.service.LocationInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy Date:2021/2/27 17:01
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@RestController
public class LocationInfoController {

    @Autowired
    private LocationInfoService locationInfoService;

    @Autowired
    private LocationInfoDao locationInfoDao;

    /**
     * 查询所有的教学区域安排信息
     *
     * @return
     */
    @RequestMapping(value = "/locations/{page}", method = RequestMethod.GET)
    public ServerResponse queryLocationInfo(@PathVariable("page") Integer page, @RequestParam(defaultValue = "10")
            Integer limit) {
        Map<String, Object> map = new HashMap<>();
        List<LocationVO> list = locationInfoDao.locations((page - 1) * limit, limit);
        //int total = locationInfoDao.count();
        int total = 0;
        if (list != null && list.size() > 0) {
            total = list.size();
        }
        map.put("records", list);
        map.put("total", total);
        return ServerResponse.ofSuccess(map);
    }


    /**
     * 新增教学区域
     * @return
     */
    @RequestMapping(value = "/setteacharea", method = RequestMethod.POST)
    public ServerResponse setTeachArea(@RequestBody() LocationSetVO l) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("teach_build_no", l.getTeachBuildNo());
        wrapper.eq("grade_no", l.getGradeNo());
        LocationInfo locationInfo = new LocationInfo();
        if (locationInfoService.getOne(wrapper) != null) {
            return ServerResponse.ofError("该教学区域已经设置过了！");
        }
        locationInfo.setTeachBuildNo(l.getTeachBuildNo());
        locationInfo.setGradeNo(l.getGradeNo());
        boolean b = locationInfoService.save(locationInfo);
        if (b) {
            return ServerResponse.ofSuccess("设置教学区域成功");
        }
        return ServerResponse.ofError("设置教学区域失败");
    }

    /**
     * 根据id删除教学区域信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/location/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse delete(@PathVariable("id") Integer id) {
        boolean b = locationInfoService.removeById(id);
        if (b) {
            return ServerResponse.ofSuccess("删除成功");
        }
        return ServerResponse.ofError("删除失败");
    }

    /**
     * 根据id删除教学区域信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/location/modifyInfo/{id}", method = RequestMethod.POST)
    public ServerResponse modifyInfoById(@PathVariable("id") Integer id) {
     /*   boolean b = locationInfoService.updateById(id);
        if (b) {
            return ServerResponse.ofSuccess("删除成功");
        }*/
        return ServerResponse.ofError("删除失败");

    }

}
