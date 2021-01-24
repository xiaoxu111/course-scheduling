package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 19:45
 * 项目名:course-scheduling-system
 * Description:TODO
 * Version: 1.0
 **/
@Data
public class TeachbuildAddRequest {

    /**
     * 教学楼编号
     */
    private String teachBuildNo;

    /**
     * 教学楼名称
     */
    private String teachBuildName;

    /**
     * 教学楼位置
     */
    private String teachBuildLocation;
}
