package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 18:58
 * 项目名:course-scheduling-system
 * Description:
 * Version: 1.0
 **/
@Data
public class ClassroomAddRequest {
    /**
     * 教室编号
     */
    private String classRoomNo;

    /**
     * 教室名称
     */
    private String classRoomName;

    /**
     * 所属教学楼
     */
    private String teachbuildNo;

    /**
     * 教室容量
     */
    private Integer capacity;


    /**
     * 备注
     */
    private String remark;
}
