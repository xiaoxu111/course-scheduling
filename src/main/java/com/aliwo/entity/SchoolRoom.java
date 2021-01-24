package com.aliwo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author:xuyy19 Date:2021/1/24 13:32
 * 项目名:course-scheduling-system
 * Description:教室表字段实体
 * Version: 1.0
 **/
@TableName("yc_school_room") // 表的名字
public class SchoolRoom extends Model<SchoolRoom> {

    private static final long serialVersionUID = 1L;

    /**
     * 教室id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 教室编号
     */
    private String schoolRoomNo;

    /**
     * 教室名称
     */
    private String schoolRoomName;

    /**
     * 所在教学楼编号
     */
    private String teachbuildNo;

    /**
     * 教室人数容量
     */
    private Integer capacity;

    /**
     * 教室属性
     */
    private String attribute;

    /**
     * 备注
     */
    private String remark;
    /**
     * 逻辑删除(默认0显示，1删除)
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间,插入时自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间,插入和更新时自动填充
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
