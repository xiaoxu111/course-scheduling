package com.aliwo.entity.response;

import com.aliwo.entity.LocationInfo;
import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 18:53
 * 项目名:course-scheduling-system
 * Description:位置Vo
 * Version: 1.0
 **/
@Data
public class LocationVO extends LocationInfo {

    /**
     * 教学楼名
     */
    private String teachbuildName;

    /**
     * 年级名
     */
    private String gradeName;
}
