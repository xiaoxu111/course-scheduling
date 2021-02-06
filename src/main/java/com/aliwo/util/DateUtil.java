package com.aliwo.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * package_name:com.aliwo.util
 *
 * @author:xuyy19 Date:2021/1/25 17:21
 * 项目名:course-scheduling
 * Description:日期工具类
 * Version: 1.0
 **/

public class DateUtil {
    /**
     * @param date
     * @return 日期格式转换成字符串
     */
    public static final String DateToString(Date date, String format) {
        if (Strings.isEmpty("date")) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @param str
     * @param format
     * @return date
     * @throws Exception 字符串转换成日期
     */
    public static final Date StringToDate(String str, String format) throws Exception {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }
}
