package com.aliwo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author:xuyy19 Date:2021/1/24 15:38
 * 项目名:course-scheduling-system
 * Description:学习文档,作业(以文档形式下发,学生下载查看或者在线预览)
 * Version: 1.0
 **/

@TableName("yc_doc")
@Data
public class Doc extends Model<Doc> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * doc文件名
     */
    private String docName;

    /**
     * 自定义的名字
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String docUrl;

    /**
     * 文件描述
     */
    private String description;

    /**
     * 目标班级
     */
    private String toClassNo;

    /**
     * 发布者id
     */
    private Integer fromUserId;

    /**
     * 发布者名字
     */
    private String fromUserName;

    /**
     * 来自的用户类型 1:管理员。2：讲师
     */
    private String fromUserType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 阅读次数
     */
    private Long clicks;

    /**
     * 有效天数
     */
    private Integer expire;
    /**
     * 逻辑删除(默认0显示，1删除)
     */
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间,插入时自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

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
