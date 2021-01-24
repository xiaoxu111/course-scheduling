package com.aliwo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author:xuyy19 Date:2021/1/24 15:41
 * 项目名:course-scheduling-system
 * Description:出题表(题库)
 * Version: 1.0
 **/

@TableName("yc_exercise")
@Data
public class Exercise extends Model<Exercise> {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 题目类别id
     */
    private Integer categoryId;

    /**
     * 题目所属班级，编号
     */
    private String classNo;

    /**
     * 题目名称
     */
    private String exerciseTitle;

    /**
     * 是否多选，默认0单选，，1多选
     */
    private Integer multiselect;

    /**
     * 选项
     */
    private String answer;

    /**
     * 选项A的值
     */
    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String optionE;

    private String optionF;

    /**
     * 分值
     */
    private Integer fraction;

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
