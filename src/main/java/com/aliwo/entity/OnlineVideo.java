package com.aliwo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author:xuyy19 Date:2021/1/24 18:40
 * 项目名:course-scheduling-system
 * Description:视频信息表
 * Version: 1.0
 **/
@TableName("yc_online_video")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnlineVideo extends Model<OnlineVideo> {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属在线课程id
     */
    private Integer onlineCourseId;

    /**
     * 视频编号
     */
    private String videoNo;

    /**
     * 视频名称
     */
    private String videoName;

    /**
     * 视频封面
     */
    private String cover;

    /**
     * 视频路径
     */
    private String videoUrl;

    /**
     * 上传用户类型，1管理员，2讲师
     */
    private Integer fromUserType;

    /**
     * 上传者id
     */
    private Integer fromUserId;

    /**
     * 上传者名称
     */
    private String fromUserName;
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
