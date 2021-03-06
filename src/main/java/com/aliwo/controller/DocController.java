package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.Doc;
import com.aliwo.entity.request.DocsVO;
import com.aliwo.service.DocService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy19 Date:2021/2/8 10:24
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

    /**
     * 分页查询所有的文档
     * @author xuyayuan
     * @date 2021/4/5 17:41
     * @param page
     * @param limit
     * @return com.aliwo.common.ServerResponse
     */
    @RequestMapping(value = "/docs/{page}", method = RequestMethod.GET)
    public ServerResponse allDocs(@PathVariable("page") Integer page, @RequestParam(defaultValue = "5") Integer limit) {
        Page<Doc> pages = new Page<>(page, limit);
        QueryWrapper<Doc> wrapper = new QueryWrapper<Doc>().orderByDesc("create_time");
        IPage<Doc> iPage = docService.page(pages, wrapper);
        return ServerResponse.ofSuccess(iPage);
    }

    /**
     * 根据id删除文档
     * @author xuyayuan
     * @date 2021/4/5 17:46
     * @param id
     * @return com.aliwo.common.ServerResponse
     */
    @RequestMapping(value = "/deletedoc", method = RequestMethod.DELETE)
    public ServerResponse delete(@RequestParam Integer id) {
        boolean b = docService.removeById(id);
        if (b) {
            return ServerResponse.ofSuccess("删除成功");
        }
        return ServerResponse.ofError("删除失败");
    }

    /**
     * 添加文档的相关描述信息
     * @author xuyayuan
     * @date 2021/4/5 19:38
     * @param docsVO
     * @return com.aliwo.common.ServerResponse
     */
    @PostMapping("/adddocs")
    public ServerResponse addDocs(@RequestBody DocsVO docsVO) {
        // 检查上传的文档参数
        ServerResponse serverResponse = this.checkUploadDocument(docsVO);
        if (serverResponse != null) return serverResponse;
        return docService.addDcos(docsVO);
    }

    /**
     * 上传DOC文档，只负责上传并返回文件的url
     * @param file
     * @return
     */
    @PostMapping("/uploaddocs")
    public ServerResponse uploadDocs(MultipartFile file) {
        if (null == file) {
            return ServerResponse.ofError("请选择要上传的文档！！！");
        }
        return docService.uploadDocs(file);
    }

    /**
     * 检查上传的文档参数
     * @author xuyayuan
     * @date 2021/4/5 20:35
     * @param docsVO
     * @return com.aliwo.common.ServerResponse
     */
    private ServerResponse checkUploadDocument(DocsVO docsVO) {
        if (StringUtils.isEmpty(docsVO.getDocUrl())) {
            return ServerResponse.ofError("请上传文档");
        }
        if (StringUtils.isEmpty(docsVO.getDocName())) {
            return ServerResponse.ofError("请上传文档");
        }
        if (StringUtils.isEmpty(docsVO.getFileName())) {
            return ServerResponse.ofError("请填写文件名");
        }
        if (StringUtils.isEmpty(docsVO.getToClassNo())) {
            return ServerResponse.ofError("请填写推送班级编号");
        }
        if (null == docsVO.getExpire()) {
            return ServerResponse.ofError("请填写过期时间");
        }
        return null;
    }
}
