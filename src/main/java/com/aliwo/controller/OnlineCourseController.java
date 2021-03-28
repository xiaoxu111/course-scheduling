package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.OnlineCourse;
import com.aliwo.entity.request.OnlineCourseAddVO;
import com.aliwo.service.OnlineCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy Date:2021/2/28 20:47
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/

@RestController
@RequestMapping("/onlinecourse")
public class OnlineCourseController {

    @Autowired
    private OnlineCourseService onlineCourseService;


    /**
     * 根据二级分类id查询网课
     *
     * @param id 二级分类id
     * @return
     */
    @GetMapping("/get-list/{id}/{page}/{limit}")
    public ServerResponse queryByCategory(@PathVariable("id") Integer id,
                                          @PathVariable("page") Integer page,
                                          @PathVariable("limit") Integer limit) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("online_category_id", id);
        Page<OnlineCourse> pages = new Page<>(page, limit);
        IPage<OnlineCourse> iPage = onlineCourseService.page(pages, wrapper);
        if (page != null) {
            return ServerResponse.ofSuccess(iPage);
        }
        return ServerResponse.ofError("查询不到数据");
    }

    /**
     * 添加网课
     *
     * @param on
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse addCourse(@RequestBody OnlineCourseAddVO on) {
        if (StringUtils.isEmpty(on.getFromUserName())){
            return ServerResponse.ofError("系统没有找到相应的上传者，失败！！！");
        }
        if (null == String.valueOf(on.getFromUserId())){
            return ServerResponse.ofError("系统没有找到相应的上传者ID，失败！！！");
        }
        if (StringUtils.isEmpty(on.getOnlineName())){
            return ServerResponse.ofError("请填写网课名称");
        }
        if (StringUtils.isEmpty(on.getDescription())){
            return ServerResponse.ofError("请填写课程简介");
        }
        OnlineCourse onlineCourse = new OnlineCourse();
        // 获取网课编号
        String no = this.getCourseNo();
        if (StringUtils.isEmpty(no)) return ServerResponse.ofError("获取网课编号失败！！！");
        onlineCourse.setOnlineNo(no);
        onlineCourse.setOnlineName(on.getOnlineName());
        onlineCourse.setDescription(on.getDescription());
        onlineCourse.setCover(on.getCover());
        onlineCourse.setOnlineCategoryId(on.getOnlineCategoryId());
        onlineCourse.setOnlineCategoryName(on.getOnlineCategoryName());
        onlineCourse.setFromUserId(on.getFromUserId());
        onlineCourse.setFromUserType(on.getFromUserType());
        onlineCourse.setFromUserName(on.getFromUserName());
        boolean b = onlineCourseService.save(onlineCourse);
        if (b) return ServerResponse.ofSuccess("添加网课成功");
        return ServerResponse.ofError("添加网课失败");
    }

    /**
     * 自动获取网课编号
     *
     * @return
     */
    private String getCourseNo() {
        QueryWrapper wrapper = new QueryWrapper();
        // 按照网络编号降序排列
        wrapper.select("online_no").orderByDesc("online_no");
        List<OnlineCourse> list = onlineCourseService.list(wrapper);
        String no = String.valueOf(Integer.parseInt(list.get(0).getOnlineNo()) + 1);
        return no;
    }


    /**
     * 更新网课信息
     * @param id
     * @param onlineCourse
     * @return
     */
    @PostMapping("/modify/{id}")
    public ServerResponse modifyCourseInfo(@PathVariable("id") Integer id, @RequestBody OnlineCourse onlineCourse) {
        QueryWrapper<OnlineCourse> wrapper = new QueryWrapper<OnlineCourse>().eq("id", id);
        boolean b = onlineCourseService.update(onlineCourse, wrapper);
        if (b) {
            return ServerResponse.ofSuccess("更新成功");
        }
        return ServerResponse.ofError("更新失败");
    }


}
