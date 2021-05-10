package com.aliwo.service;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.ClassTask;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * package_name:com.aliwo.service
 *
 * @author:xuyy Date:2021/2/28 17:53
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
public interface ClassTaskService extends IService<ClassTask> {

    ServerResponse classScheduling(@Param("semester") String semester);
}
