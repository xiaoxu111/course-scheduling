package com.aliwo.dao;

import com.aliwo.entity.ClassRoom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * package_name:com.aliwo.dao
 *
 * @author:xuyy19 Date:2021/2/6 12:54
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface ClassRoomDao extends BaseMapper<ClassRoom> {

    //    查询某个教学楼下的教室列表
    @Select("select * from yc_class_room where teachbuild_no = #{teachbuildNo}")
    List<ClassRoom> selectByTeachbuildNo(@Param("teachbuildNo") String teachbuildNo);
}
