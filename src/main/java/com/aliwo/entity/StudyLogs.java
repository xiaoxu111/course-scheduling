package com.aliwo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author:xuyy19 Date:2021/1/24 18:44
 * 项目名:course-scheduling-system
 * Description:学习记录表
 * Version: 1.0
 **/
@TableName("yc_study_logs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyLogs extends Model<StudyLogs> {
    private static final long serialVersionUID = 1L;

    /**
     * 学生学习记录表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 网课id
     */
    private Integer onlineCourseId;

    /**
     * 网课名称
     */
    private String onlineName;

    /**
     * 看到哪个视频了
     */
    private Integer videoId;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
