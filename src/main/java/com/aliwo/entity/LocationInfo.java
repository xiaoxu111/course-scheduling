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
 * package_name:com.aliwo.entity
 *
 * @author:xuyy19 Date:2021/1/24 15:45
 * 项目名:course-scheduling-system
 * Description:位置信息，例如高二的教学楼有哪些 位置信息表
 * Version: 1.0
 **/
@TableName("yc_location_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfo extends Model<LocationInfo> {
    private static final long serialVersionUID = 1L;

    /**
     * id,位置信息，高一在哪栋楼，高二在哪
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 教学楼编号,放教学楼表中编号
     */
    private String teachBuildNo;

    /**
     * 年级编号,放年级表中的id
     */
    private String gradeNo;
    /**
     * 逻辑删除(默认0显示，1删除)
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间,插入时自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createTime;

    /**
     * 更新时间,插入和更新时自动填充
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
