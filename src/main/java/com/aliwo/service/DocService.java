package com.aliwo.service;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.Doc;
import com.aliwo.entity.request.DocsVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * package_name:com.aliwo.service
 *
 * @author:xuyy19 Date:2021/2/8 10:22
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface DocService extends IService<Doc> {

    // 上传文档
    ServerResponse uploadDocs(MultipartFile file);

    ServerResponse addDcos(DocsVO docsVO);
}
