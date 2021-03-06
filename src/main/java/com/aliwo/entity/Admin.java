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
 * @author:xuyy19 Date:2021/1/24 12:55
 * 项目名:course-scheduling-system
 * Description: 管理员表字段实体
 * Version: 1.0
 **/
@TableName("yc_admin")  //表命
@AllArgsConstructor // 全部参数构造函数的生成
@NoArgsConstructor // 无参构造函数的生成
@Data
public class Admin extends Model<Admin> {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员表主键 d
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    /**
     * 管理员编号
     */
    private String adminerNo;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码  01ffda11f297fdbfd9ea6c94c2aa155df550f583
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 用户类型 1(管理员)
     */
    private Integer userType;

    /**
     * 职称
     */
    private String jobTitle;

    /**
     * 教授科目
     */
    private String teachSubject;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 证件
     */
    private String license;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 地址
     */
    private String address;

    /**
     * 签名
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

    /**
     * 优先级
     */
    private Integer piority;

    /**
     * 权限,默认为1(普通管理员) 0为超级管理员,登录的时候需要进行验证
     */
    private Integer power;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 账户状态
     */
    private Integer status;

    /**
     * 表字段逻辑处理注解（逻辑删除,默认0显示，1删除）
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间 字段注解,插入值时自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createTime;

    /**
     * 更新时间,字段注解,插入和更新字段时自动填充
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
