package com.aliwo.dao;

import com.aliwo.entity.ClassTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * package_name:com.aliwo.dao
 *
 * @author:xuyy Date:2021/2/28 17:43
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Mapper
@Component
public interface ClassTaskDao extends BaseMapper<ClassTask> {
    @Update("truncate yc_class_task")
    void clearClassTaskOld();

    @Select("SELECT distinct class_no, grade_no FROM yc_class_task")
    List<String> selectClassNo();


    // 可能这里是columnName没传进来
    @Select("select distinct ${columnName} from yc_class_task")
    List<String> selectByColumnName(@Param("columnName") String columnName);

}
