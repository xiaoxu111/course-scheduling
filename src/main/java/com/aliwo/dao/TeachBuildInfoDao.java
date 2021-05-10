package com.aliwo.dao;

import com.aliwo.entity.TeachbuildInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * package_name:com.aliwo.dao
 *
 * @author:xuyy19 Date:2021/2/6 12:08
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface TeachBuildInfoDao extends BaseMapper<TeachbuildInfo> {

    @Select("select teach_build_no from yc_location_info where grade_no = #{gradeNo}")
    List<String> selectTeachBuildList(@Param("gradeNo") String gradeNo);
}
