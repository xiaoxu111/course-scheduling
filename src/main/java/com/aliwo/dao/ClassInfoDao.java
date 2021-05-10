package com.aliwo.dao;

import com.aliwo.entity.ClassInfo;
import com.aliwo.entity.response.ClassInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * package_name:com.aliwo.dao
 *
 * @author:xuyy19 Date:2021/2/4 15:47
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface ClassInfoDao extends BaseMapper<ClassInfo> {

    /**
     * @param page
     * @param limit
     * @return List<ClassInfoVo>
     *     分页查询所有班级信息
     */
    List<ClassInfoVo> queryClassInfos(@Param("page")Integer page, @Param("limit")Integer limit);

    /**
     * @return
     */
    int count2();

    /**
     * @param page
     * @param limit
     * @param gradeNo
     * @return
     */
    List<ClassInfoVo> queryClassInfoByGradeNo(@Param("page") Integer page, @Param("limit")Integer limit, @Param("gradeNo")String gradeNo);


    /**
     * @param gradeNo
     * @return
     */
    int count1(@Param("gradeNo")String gradeNo);

    // 获得班级的人数
    @Select("select num from yc_class_info where class_no = #{classNo}")
    int selectStuNum(@Param("classNo") String classNo);

}
