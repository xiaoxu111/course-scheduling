package com.aliwo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * package_name:com.aliwo.common
 *
 * @author:徐亚远 Date:2021/1/24 15:02
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/

@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS(0, "success"),

    ERROR(1, "error"),

    VALIDATE_FAILED(404, "参数校验失败"),

    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    FORBIDDEN(403, "没有相关权限");

    // 响应状态码
    private int code;

    // 提示信息
    private String desc;
}
