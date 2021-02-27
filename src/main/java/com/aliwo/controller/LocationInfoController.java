package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.dao.LocationInfoDao;
import com.aliwo.entity.response.LocationVO;
import com.aliwo.service.LocationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * package_name:com.aliwo.controller
 *
 * @author:徐亚远 Date:2021/2/27 17:01
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
}
