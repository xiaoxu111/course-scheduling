package com.aliwo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
    @TableField("grade_no")
    private String gradeNo;

    /**
     * 班级编号
     */
    @TableField("class_no")
    private String classNo;

    /**
     * 课程编号
     */
    @TableField("course_no")
    private String courseNo;

    /**
     * 讲师编号
     */
    @TableField("teacher_no")
    private String teacherNo;

    /**
     * 教室编号
     */
    @TableField("classroom_no")
    private String classroomNo;

    /**
     * 上课时间
     */
    @TableField("class_time")
    private String classTime;

    /**
     * 周数
     */
    @TableField("weeks_sum")
    private Integer weeksSum;

    /**
     * 学期
     */
    @TableField("semester")
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
