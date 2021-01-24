package com.aliwo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author:徐亚远 Date:2021/1/24 15:44
 * 项目名:course-scheduling-system
 * Description:年级表
 * Version: 1.0
 **/
@TableName("yc_grade_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeInfo extends Model<GradeInfo> {
    private static final long serialVersionUID=1L;

    /**
     * id,年级表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 年级编号
     */
    private String gradeNo;

    /**
     * 年级名称
     */
    private String gradeName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
