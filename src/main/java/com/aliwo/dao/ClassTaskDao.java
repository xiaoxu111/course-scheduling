package com.aliwo.dao;

import com.aliwo.entity.ClassTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * package_name:com.aliwo.dao
 *
 * @author:徐亚远 Date:2021/2/28 17:43
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Mapper
@Component
public interface ClassTaskDao extends BaseMapper<ClassTask> {

    @Update("truncate tb_class_task")
    void clearClassTaskOld();

}
