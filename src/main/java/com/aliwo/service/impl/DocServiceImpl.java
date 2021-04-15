package com.aliwo.service.impl;

import com.aliwo.common.ServerResponse;
import com.aliwo.dao.DocDao;
import com.aliwo.entity.Doc;
import com.aliwo.entity.request.DocsVO;
import com.aliwo.service.DocService;
import com.aliwo.util.AliyunUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

/**
 * package_name:com.aliwo.service.impl
 *
 * @author:xuyy19 Date:2021/2/8 10:22
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Service
public class DocServiceImpl extends ServiceImpl<DocDao, Doc> implements DocService {
    private static final Logger LOG = LoggerFactory.getLogger(DocServiceImpl.class);
    @Autowired
    private DocService docService;

    /**
     * 上传文档
     * @author xuyayuan
     * @date 2021/4/5 20:39
     * @param file 
     * @return com.aliwo.common.ServerResponse
     */
    @Override
    public ServerResponse uploadDocs(MultipartFile file) {
        Map map = AliyunUtil.upload(file, null);
        LOG.info("docUrl文件路径为" + map.get("url"));
        return ServerResponse.ofSuccess(map);
    }

    /**
     * 添加文档
     * @author xuyayuan
     * @date 2021/4/5 20:39
     * @param d 
     * @return com.aliwo.common.ServerResponse
     */
    @Override
    public ServerResponse addDcos(DocsVO d) {
        Doc doc = new Doc();
        doc.setDescription(d.getDescription());
        doc.setDocName(d.getDocName());
        doc.setFileName(d.getFileName());
        doc.setDocUrl(d.getDocUrl());
        doc.setExpire(d.getExpire());
        doc.setFromUserId(d.getFromUserId());
        doc.setFromUserName(d.getFromUserName());
        doc.setFromUserType(String.valueOf(d.getFromUserType()));
        doc.setToClassNo(d.getToClassNo());
        doc.setCreateTime(new Date());
        boolean b = docService.save(doc);
        if (b) {
            return ServerResponse.ofSuccess("添加文档成功");
        }
        return ServerResponse.ofError("添加文档失败");
    }
}
