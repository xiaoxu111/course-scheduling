package com.aliwo.dao;

import com.aliwo.entity.LocationInfo;
import com.aliwo.entity.response.LocationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * package_name:com.aliwo.dao
 *
 * @author:徐亚远 Date:2021/2/27 16:27
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface LocationInfoDao extends BaseMapper<LocationInfo> {

    @Select("select tli.id, ttbi.teach_build_no, ttbi.teach_build_name, tgi.grade_no,tgi.grade_name from " +
            "yc_teach_build_info ttbi join yc_location_info tli on tli.teach_build_no = ttbi.teach_build_no join " +
            "yc_grade_info tgi on tli.grade_no = tgi.grade_no where tli.deleted = 0 limit ${page}, ${limit}")
    List<LocationVO> locations(Integer page, Integer limit);

    @Select("select count(*) from yc_teach_build_info ttbi join yc_location_info tli on tli.teach_build_no " +
            "= ttbi" +
            ".teach_build_no join yc_grade_info tgi on tli.grade_no = tgi.grade_no")
    int count();
}
