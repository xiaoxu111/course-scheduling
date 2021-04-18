package com.aliwo.stream.lambda;

import org.junit.Test;

import java.util.Locale;

/**
 * @author xuyayuan
 * @data 2021年04月18日
 */
public class TestLambda {


    /**
     * 2.1声明函数式接口，接口中声明抽象方法，public String getValue(String str);
     * <p>
     * 2.2声明类TestLambda，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值。
     * <p>
     * 2.3再将一个字符串的第2个和第四个索引位置进行截取字串。
     *
     * @author xuyayuan
     * @date 2021/4/18 13:20
     */
    @Test
    public void test01() {
        String upStr = operation("hello world", (str) ->str.toUpperCase(Locale.ROOT));
        System.out.println(" 小写转大写upStr： " + upStr);
        System.out.println("2-4的截取："+ operation("hello world",e -> e.substring(2, 5)));
    }

    public String operation(String str, TestFun testFun) {
        return testFun.getValue(str);
    }
}
