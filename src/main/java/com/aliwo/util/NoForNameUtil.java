package com.aliwo.util;

/**
 * package_name:com.aliwo.util
 *
 * @author:xuyy19 Date:2021/2/6 13:04
 * 项目名:course-scheduling
 * Description: 教学楼编号与教学楼名称的映射关系
 * Version: 1.0
 **/

public class NoForNameUtil {
    /**
     * @param teacherBuildNo
     * @return String
     * 根据教学楼编号返回相应的教学楼名称
     */
    public static final String getTeachBuildName(String teacherBuildNo) {
        switch (teacherBuildNo) {
            case "01":
                return "第1教学楼";
            case "02":
                return "2号教学楼";
            case "03":
                return "3号教学楼";
            case "04":
                return "4号教学楼";
            case "05":
                return "5号教学楼";
            case "06":
                return "音乐楼";
            case "07":
                return "美术楼";
            case "08":
                return "实验楼1";
            case "09":
                return "实验楼2";
            case "10":
                return "逸夫楼1";
            case "11":
                return "逸夫楼2";
            case "12":
                return "体育楼";
            case "13":
                return "化生楼";
            case "14":
                return "14号教学楼";
            case "20":
                return "测试楼";
            default:
                return "";
        }
    }
}
