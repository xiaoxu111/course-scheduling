package com.aliwo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * package_name:com.aliwo.entity
 *
 * @author:xuyy19 Date:2021/1/24 15:43
 * 项目名:course-scheduling-system
 * Description:TODO
 * Version: 1.0
 **/
@TableName("yc_exercise_category")
@Data
public class ExerciseCategory extends Model<ExerciseCategory> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 题目类别名称
     */
    private String categoryName;
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
