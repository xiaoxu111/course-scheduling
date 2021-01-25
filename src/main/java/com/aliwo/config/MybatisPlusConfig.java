package com.aliwo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * package_name:com.aliwo.config
 *
 * @author:xuyy19 Date:2021/1/24 15:25
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/

@Configuration
@MapperScan("com.aliwo.dao")
public class MybatisPlusConfig {
    // 分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    // 逻辑删除插件,MP3.1之后不需要配置

}
