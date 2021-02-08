package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.Admin;
import com.aliwo.entity.Doc;
import com.aliwo.service.DocService;
import com.aliwo.util.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * package_name:com.aliwo.controller
 *
 * @author:徐亚远 Date:2021/2/8 10:24
 * 项目名:course-scheduling
 * Description:学生文档Controller
 * Version: 1.0
 **/
@RestController
public class DocController {
    private static final Logger LOG = LoggerFactory.getLogger(DocController.class);
    @Autowired
    private DocService docService;


    /**
     * 根据班级查询文档，用于学生端,查看文档操作
     * @param page
     * @param toClassNo
     * @param limit
     * @return
     */
    @RequestMapping(value = "/docs-class/{page}/{toClassNo}", method = RequestMethod.GET)
    public ServerResponse getDocsByClass(@PathVariable("page") Integer page, @PathVariable("toClassNo") String toClassNo,
                                         @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        Page<Doc> pages = new Page<>(page, limit);
        QueryWrapper<Doc> wrapper = new QueryWrapper<Doc>().orderByDesc("create_time").eq("to_class_no", toClassNo);
        IPage<Doc> iPage = docService.page(pages, wrapper);
        if (null == iPage || iPage.getTotal() == 0 || iPage.getTotal() ==0){
            return ServerResponse.ofError("没有查到学生文档信息！！！");
        }
        List<Doc> docList = iPage.getRecords();
        for (Doc doc :docList){
            switch (doc.getFromUserType()) {
                case "1":
                    doc.setFromUserType("管理员");
                    break;
                case "2":
                    doc.setFromUserType("讲师");
                    break;
                default:
                    doc.setFromUserType("学生");
                    break;
            }
        }
        LOG.info("iPage信息："+iPage);
        return ServerResponse.ofSuccess(iPage);
    }

    /**
     * 学生端查看文档点击下载文档后点击次数的修改+1
     * @param id
     * @return
     */
    @RequestMapping(value = "/alterclick/{id}", method = RequestMethod.GET)
    public ServerResponse alterClick(@PathVariable("id") Integer id) throws Exception {
        if (Strings.isEmpty(id.toString())) {
            return ServerResponse.ofError("id为空，请检查！！！");
        }
        QueryWrapper<Doc> wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        Doc doc = docService.getOne(wrapper);
        if (null == doc) {
            return ServerResponse.ofError("没有查询到相应的文档信息！！！");
        }
        // 点击次数+!
        doc.setClicks(doc.getClicks()+1);
        wrapper.eq("clicks", doc.getClicks());
        boolean flag = docService.updateById(doc);
        if (!flag){
            return ServerResponse.ofError("更新点击次数失败！！！");
        }
        if (doc.getClicks() >1000){
            return ServerResponse.ofSuccess(doc.getDocName()+" 属于热门文档 !!!");
        }else {
            return ServerResponse.ofSuccess(doc.getDocName()+" 下载了"+"【"+doc.getClicks()+"次"+"】");
        }
    }
}