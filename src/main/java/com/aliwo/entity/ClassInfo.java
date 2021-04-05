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
 * @author:xuyy19 Date:2021/1/24 13:21
 * 项目名:course-scheduling-system
 * Description: 班级信息表字段实体
 * Version: 1.0
 **/

@TableName("yc_class_info") //表名
@AllArgsConstructor // 全部参数构造函数的生成
@NoArgsConstructor // 无参构造函数的生成
@Data
public class ClassInfo extends Model<ClassInfo> {
    private static final long serialVersionUID = 1l;

    /**
     * 班级表主键 id, 自增长
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班级人数
     */
    private Integer num;

    /**
     * 班主任id
     */
    private Integer teacherId;

    /**
     * 直接用来做为年级编号的划分了 用于根据年级查询班级信息
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
