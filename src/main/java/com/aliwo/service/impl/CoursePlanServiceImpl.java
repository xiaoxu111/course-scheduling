package com.aliwo.service.impl;

import com.aliwo.dao.CoursePlanDao;
import com.aliwo.entity.CoursePlan;
import com.aliwo.service.CoursePlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * package_name:com.aliwo.service.impl
 *
 * @author:xuyy19 Date:2021/2/6 13:27
 * 项目名:course-scheduling
 * Description: 排课信息服务
 * Version: 1.0
 **/
@Service
public class CoursePlanServiceImpl extends ServiceImpl<CoursePlanDao, CoursePlan> implements CoursePlanService {
}
