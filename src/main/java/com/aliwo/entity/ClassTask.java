package com.aliwo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
 * @author:xuyy19 Date:2021/1/24 15:15
 * 项目名:course-scheduling-system
 * Description:课程计划表
 * Version: 1.0
 **/

@TableName("yc_class_task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("课程计划")
public class ClassTask extends Model<ClassTask> {
    private static final long serialVersionUID = 1L;

    /**
     * id，即将要上课的，需要进行排课的
     */
    @ExcelIgnore
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学期
     */
    @Excel(name = "学期")
    private String semester;

    /**
     * 年级编号
     */
    @Excel(name = "年级")
    private String gradeNo;

    /**
     * 班级编号
     */
    @Excel(name = "班级")
    private String classNo;

    /**
     * 课程编号
     */
    @Excel(name = "课程编号")
    private String courseNo;

    /**
     * 课程名称
     */
    @Excel(name = "课程名")
    private String courseName;

    /**
     * 讲师编号
     */
    @Excel(name = "讲师编号")
    private String teacherNo;

    /**
     * 讲师名字
     */
    @Excel(name = "讲师姓名")
    private String realname;

    /**
     * 课程属性
     */
    @Excel(name = "课程属性")
    private String courseAttr;

    /**
     * 学生人数
     */
    @Excel(name = "学生人数")
    private Integer studentNum;

    /**
     * 周数
     */
    @Excel(name = "周数")
    private Integer weeksSum;

    /**
     * 周学时，偶数
     */
    @Excel(name = "周学时")
    private Integer weeksNumber;

    /**
     * 是否固定上课时间
     */
    @Excel(name = "是否固定上课时间")
    private String isFix;

    /**
     * 2位为一个时间编号
     */
    @Excel(name = "上课时间")
    private String classTime;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
