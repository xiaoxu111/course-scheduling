package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 19:40
 * 项目名:course-scheduling-system
 * Description:TODO
 * Version: 1.0
 **/
@Data
public class LocationSetVO {

    /**
     * 教学楼编号
     */
    private String teachBuildNo;

    /**
     * 年级号
     */
    private String gradeNo;
}
