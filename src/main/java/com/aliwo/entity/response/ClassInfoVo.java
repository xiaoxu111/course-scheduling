package com.aliwo.entity.response;

import com.aliwo.entity.ClassInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author:xuyy19 Date:2021/1/24 18:49
 * 项目名:course-scheduling-system
 * Description:班级信息VO
 * Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassInfoVo extends ClassInfo {

    /**
     * 班主任姓名
     */
    private String realname;

    /**
     * 班级名称
     */
    private String gradeName;
}
