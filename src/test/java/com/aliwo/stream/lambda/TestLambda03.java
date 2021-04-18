package com.aliwo.stream.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java8 四大内置函数式接口
 * 一：Consumer<T>：消费型接口
 * <p>
 * void accept(T t);
 * <p>
 * 二：Supplier<T>：供给型接口
 * <p>
 * T get();
 * <p>
 * 三：Function(T, R)：函数式接口
 * <p>
 * R apply(T, t);
 * <p>
 * 四：Predicate<T>：断言型接口
 * <p>
 * boolean test(T t);
 *
 * @author xuyayuan
 * @data 2021年04月18日
 */
public class TestLambda03 {

    /**
     * 一：Consumer<T>：消费型接口 void accept(T t);
     *
     * @author xuyayuan
     * @date 2021/4/18 19:36
     */
    @Test
    public void test01() {
        happy(2000, (e) -> System.out.println("共消费了" + e + "万元"));

    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    /**
     * 二：Supplier<T>：供给型接口 T get();
     *
     * @author xuyayuan
     * @date 2021/4/18 19:42
     */
    @Test
    public void test02() {
       getList(10, () -> (int)(Math.random() * 100))
                            .forEach(System.out::println);
    }

    /**
     * 产生指定个数的整数,并放入到集合中
     *二：Supplier<T>：供给型接口 T get();
     * @author xuyayuan
     * @date 2021/4/18 19:45
     */
    public List<Integer> getList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }

    /**
     * 三：Function(T, R)：函数式接口 R apply(T, t);
     *
     * @author xuyayuan
     * @date 2021/4/18 19:42
     */
    @Test
    public void test03() {
        System.out.println("小写转大写："+ strHandler("hello world", (e) -> e.toUpperCase(Locale.ROOT)));
    }

    public String strHandler(String str, Function<String, String > function){
        return function.apply(str);
    }

    /**
     * 四：Predicate<T>：断言型接口 boolean test(T t);
     *  判断字符中是否包含指定的字符
     * @author xuyayuan
     * @date 2021/4/18 19:42
     */
    @Test
    public void test04() {
        List<String> list = Arrays.asList("hello","world", "home", "hose", "whose");
        preArrest(list, (e) -> e.indexOf("h") != -1)
                                .forEach(System.out::println);
    }

    /**
     * Predicate<T>：断言型接口 boolean test(T t);
     * @author xuyayuan
     * @date 2021/4/18 20:18
     * @param pre
     * @return null
     */
    public List<String> preArrest(List<String> list, Predicate<String> pre){
        List<String> list1 = new ArrayList<>();
        for (String str : list){
            if (pre.test(str)){
                list1.add(str);
            }
        }
       return list1;
    }
}
