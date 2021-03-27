package com.aliwo.service;

import com.aliwo.common.ServerResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * package_name:com.aliwo.service
 *
 * @author:xuyy Date:2021/3/21 15:31
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface UploadOrDownLoadService {

    ServerResponse uploadExcel(MultipartFile file);
}
