package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.OnlineCourse;
import com.aliwo.service.OnlineCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * package_name:com.aliwo.controller
 *
 * @author:徐亚远 Date:2021/2/28 20:47
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
}
