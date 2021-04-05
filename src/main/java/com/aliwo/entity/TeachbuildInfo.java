package com.aliwo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author:xuyy19 Date:2021/1/24 18:45
 * 项目名:course-scheduling-system
 * Description:教学楼信息表
 * Version: 1.0
 **/
@TableName("yc_teach_build_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachbuildInfo extends Model<TeachbuildInfo> {
    private static final long serialVersionUID = 1L;

    /**
     * id,教学楼信息表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
