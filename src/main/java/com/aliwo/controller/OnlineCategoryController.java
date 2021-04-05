package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.OnlineCategory;
import com.aliwo.service.OnlineCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy Date:2021/2/28 20:21
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@RestController
@RequestMapping(value = "/category")
public class OnlineCategoryController {

    @Autowired
    private OnlineCategoryService onlineCategoryService;

    /**
     * 查询所有的一级类别
     * @return
     */
    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public ServerResponse queryOne() {
        QueryWrapper wrapper = new QueryWrapper();
        // 查询父id为0的类别，即一级分类
        wrapper.eq("parent_id", 0);
        List<OnlineCategory> list = onlineCategoryService.list(wrapper);
        return ServerResponse.ofSuccess(list);
    }

    /**
     * 查询每个类别下面的二级类别
     * @param id 一级分类id
     * @return
     */
    @RequestMapping(value = "/two/{id}", method = RequestMethod.GET)
    public ServerResponse queryTwo(@PathVariable("id") Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        // 该id下面的二级分类
        wrapper.eq("parent_id", id);
        List<OnlineCategory> list = onlineCategoryService.list(wrapper);
        return ServerResponse.ofSuccess(list);
    }


    /**
     * 添加类别
     * @param categoryNo
     * @param categoryName
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse addCategory(@RequestParam(name = "categoryNo") String categoryNo,
                                      @RequestParam(name = "categoryName") String categoryName,
                                      @RequestParam(name = "remark") String remark,
                                      @RequestParam(name = "parentId") Integer parentId) {
        OnlineCategory onlineCategory = new OnlineCategory();
        onlineCategory.setCategoryNo(categoryNo);
        onlineCategory.setCategoryName(categoryName);
        onlineCategory.setParentId(parentId);
        onlineCategory.setRemark(remark);
        return onlineCategoryService.save(onlineCategory) ? ServerResponse.ofSuccess("添加成功") : ServerResponse.ofError("添加失败");
    }

    /**
     * 删除类别
     * @author xuyayuan
     * @date 2021/4/5 16:45
     * @param id 
     * @return com.aliwo.common.ServerResponse
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteCategory(@PathVariable("id") Integer id) {
        OnlineCategory onlineCategory = onlineCategoryService.getById(id);
        onlineCategory.setUpdateTime(LocalDateTime.now());
        onlineCategoryService.updateById(onlineCategory);
        if (onlineCategoryService.removeById(id)) {
            return ServerResponse.ofSuccess("删除类别成功");
        } else {
            return ServerResponse.ofError("删除类别失败");
        }
    }

}
