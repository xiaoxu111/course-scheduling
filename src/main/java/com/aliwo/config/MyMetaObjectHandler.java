package com.aliwo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * package_name:com.aliwo.config
 *
 * @author:徐亚远 Date:2021/1/24 15:58
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 自动插入公公字段
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")&&getFieldValByName("createTime",metaObject)==null) {
            setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
            //setInsertFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }

    // 自动更新公共字段
    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("updateTime")&&getFieldValByName("updateTime",metaObject)==null) {
            setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
