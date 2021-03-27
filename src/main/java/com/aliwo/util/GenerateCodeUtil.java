package com.aliwo.util;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


/**
 * package_name:com.aliwo.util
 *
 * @author:xuyy Date:2021/3/1 21:41
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/


/*
* mybatisPlus代码生成器

(1)：MP提供了大量的自定义设置，生成的代码完成能够满足各类型的需求。

(2)：MP的代码生成器和Mybatis MBG代码生成器

MP的代码生成器都是基于java代码来生成的，MBG基于xml文件进行代码的生成。

Mybatis的代码生成器可以生成，实体类，Mapper接口，Mapper映射文件

MP的代码生成器可以生成，实体类(可以选择是否支持AR)，Mapper接口，Mapper映射文件，Service层，COntroller层。

表及字段命名策略选择

在MP中，我们建议数据库表名和字段名采用驼峰命名方式，如果采用下划线命名方式，请开启全局下划线开关，如果表名字段名命名方式不一样请注解指定，我们建议最好保持一致。

这么做的原因是为了避免在对应实体类时产生的性能损耗，这样字段不用做映射就能直接和实体类对应，当然如果项目里不用考虑这点性能损耗，那么采用下划线也是没有问题的，只需要在生成代码时配置dbColumnUndeline属性就可以了。

* */

public class GenerateCodeUtil {
    public static void main(String[] args) {
        // 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor("xuyy19") // 作者
                .setOutputDir("F:\\course-scheduling\\src\\main\\java") // 生成代码路径
                .setFileOverride(true) // 文件覆盖
                .setIdType(IdType.AUTO) // 生成策略
                .setServiceName("%Service") // 设置生成的service接口的名字首字母是否为I
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        // 数据源配置
        DataSourceConfig dbConfig = new DataSourceConfig();
        dbConfig.setDbType(DbType.MYSQL) // 设置数据库类型
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/yc_course_scheduling")
                .setUsername("root")
                .setPassword("123456");

        // 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的策略
                .setTablePrefix("yc_")
                .setInclude("yc_class_task"); //生成的表

        // 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.aliwo")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
                .setXml("mapper");

        // 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dbConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        // 执行
        ag.execute();
    }
}
