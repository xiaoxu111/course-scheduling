package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.CourseInfo;
import com.aliwo.service.CourseInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 关键字查询教材信息
     * @param page
     * @param limit
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/search/{page}/{keyword}", method = RequestMethod.GET)
    public ServerResponse searchCourseInfo(@PathVariable("page") Integer page,
                                           @RequestParam(defaultValue = "10") Integer limit,
                                           @PathVariable("keyword") String keyword) {
        QueryWrapper<CourseInfo> wrapper = new QueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(keyword), "course_name", keyword);
        Page<CourseInfo> pages = new Page<>(page, limit);
        IPage<CourseInfo> iPage = courseInfoService.page(pages, wrapper);
        return ServerResponse.ofSuccess(iPage);
    }

    /**
     * 根据ID删除教材信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteCourseInfoById(@PathVariable("id") Integer id) {
        boolean b = courseInfoService.removeById(id);
        if (b) {
            return ServerResponse.ofSuccess("删除成功");
        }
        return ServerResponse.ofError("删除失败");
    }

    /**
     * 更新教材信息
     * @param id
     * @param courseInfo
     * @return
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ServerResponse modifyCourseInfo(@PathVariable("id") Integer id, @RequestBody CourseInfo courseInfo) {
        QueryWrapper<CourseInfo> wrapper = new QueryWrapper<CourseInfo>().eq("id", id);
        boolean b = courseInfoService.update(courseInfo, wrapper);
        if (b) {
            return ServerResponse.ofSuccess("更新成功");
        }
        return ServerResponse.ofError("更新失败");
    }

    /**
     * 获取添加课程的课程编号
     * @return
     */
    @RequestMapping(value = "/get-no", method = RequestMethod.GET)
    public ServerResponse getNo() {
        QueryWrapper<CourseInfo> wrapper = new QueryWrapper<CourseInfo>().select("course_no").orderByDesc("course_no");
        List<CourseInfo> list = courseInfoService.list(wrapper);
        String no = String.valueOf(Integer.parseInt(list.get(0).getCourseNo()) + 1);
        System.out.println(no + "=========");
        return ServerResponse.ofSuccess(no);
    }

    /**
     * 添加教材信息
     * @param cinfo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse addCourseInfo(@RequestBody CourseInfo cinfo) {
        boolean b = courseInfoService.saveOrUpdate(cinfo);
        if (b) {
            return ServerResponse.ofSuccess("添加成功");
        }
        return ServerResponse.ofError("添加失败");
    }

}
