package com.aliwo.controller;
/**
 * package_name:com.aliwo.controller
 *
 * @author:徐亚远 Date:2021/4/5 19:03
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.CourseInfo;
import com.aliwo.service.CourseInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuyayuan
 * @data 2021年04月05日
 */
@RestController
@RequestMapping("/courseinfo")
public class CourseInfoController {
    private static final Logger LOG = LoggerFactory.getLogger(CourseInfoController.class);
    @Autowired
    private CourseInfoService courseInfoService;

    /**
     * 分页查询所有的教材信息
     * @author xuyayuan
     * @date 2021/4/5 19:05
     * @param page
     * @param limit
     * @return com.aliwo.common.ServerResponse
     */
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public ServerResponse queryCourseInfo(@PathVariable("page") Integer page,
                                          @RequestParam(defaultValue = "10") Integer limit) {
        Page<CourseInfo> pages = new Page<>(page, limit);
        QueryWrapper<CourseInfo> wrapper = new QueryWrapper<CourseInfo>().orderByAsc("course_no");
        IPage<CourseInfo> ipage = courseInfoService.page(pages, wrapper);
        return ServerResponse.ofSuccess(ipage);
    }
}
