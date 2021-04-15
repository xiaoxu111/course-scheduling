package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 19:38
 * 项目名:course-scheduling-system
 * Description:TODO
 * Version: 1.0
 **/
@Data
public class DocsVO {

    /**
     * 接收班级
     */
    private String toClassNo;

    private String docName;

    private String fileName;

    // 文件所在的路径
    private String docUrl;

    /**
     * 文件描述
     */
    private String description;

    private Integer expire;

    private String fromUserName;

    private Integer fromUserType;

    private Integer fromUserId;

    // 上传时间需要显示在前端

}
