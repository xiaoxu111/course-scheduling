package com.aliwo.entity;

import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * package_name:com.aliwo.entity
 *
 * @author:xuyy19 Date:2021/2/6 12:48
 * 项目名:course-scheduling
 * Description:教室信息实体
 * Version: 1.0
 **/
@TableName("yc_class_room")
@Data
public class ClassRoom extends Model<ClassRoom> {
    private static final long serialVersionUID = 1L;
    /**
     * 教室id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 教室编号
     */
    private String classRoomNo;

    /**
     * 教室名称
     */
    private String classRoomName;

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
    @ExcelIgnore
    @TableLogic
    private Boolean deleted;

    /**
     * 创建时间,插入时自动填充
     */
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createTime;

    /**
     * 更新时间,插入和更新时自动填充
     */
    @ExcelIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updateTime;
}
