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
 * @author:xuyy19 Date:2021/1/24 15:28
 * 项目名:course-scheduling-system
 * Description: 排课表
 * Version: 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("yc_course_plan")
public class CoursePlan extends Model<CoursePlan> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 年级编号
     */
    private String gradeNo;

    /**
     * 班级编号
     */
    private String classNo;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 讲师编号
     */
    private String teacherNo;

    /**
     * 教室编号
     */
    private String classRoomNo;

    /**
     * 上课时间
     */
    private String classTime;

    /**
     * 周数
     */
    private Integer weeksSum;

    /**
     * 学期
     */
    private String semester;
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
