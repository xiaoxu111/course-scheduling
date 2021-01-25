package com.aliwo.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * package_name:com.aliwo.common
 *
 * @author:xuyy19 Date:2021/1/24 15:40
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
