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
 * @author:xuyy19 Date:2021/1/24 15:26
 * 项目名:course-scheduling-system
 * Description:课程信息表
 * Version: 1.0
 **/
@TableName("yc_course_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseInfo extends Model<CourseInfo> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程属性
     */
    private String courseAttr;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 课程状态
     */
    private Integer status;

    /**
     * 优先级
     */
    private Integer piority;

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
