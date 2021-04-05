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
 * @author:xuyy19 Date:2021/1/24 18:42
 * 项目名:course-scheduling-system
 * Description:学生信息表
 * Version: 1.0
 **/
@TableName("yc_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Model<Student> {
    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学号，可以用于登录
     */
    private String studentNo;

    /**
     * 昵称，可以用于登录
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 标识用户类型3
     */
    private Integer userType;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 年级
     */
    private String grade;

    /**
     * 所在班级
     */
    private String classNo;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 当前住址
     */
    private String address;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 签名
     */
    private String description;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 账号状态,0为正常，1为封禁
     */
    private Integer status;

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
