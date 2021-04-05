package com.aliwo.service.impl;

import com.aliwo.common.ServerResponse;
import com.aliwo.dao.DocDao;
import com.aliwo.entity.Doc;
import com.aliwo.entity.request.DocsVO;
import com.aliwo.service.DocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private DocService docService;
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

        boolean b = docService.save(doc);
        if (b) {
            return ServerResponse.ofSuccess("添加文档成功");
        }
        return ServerResponse.ofError("添加文档失败");
    }
}
