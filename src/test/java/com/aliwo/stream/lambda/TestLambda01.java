package com.aliwo.stream.lambda;

import com.aliwo.stream.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author xuyayuan
 * @data 2021年04月18日
 */
public class TestLambda01 {

    /**
     * Lambda表达式 格式一： 无参 无返回值
     *
     * @author xuyayuan
     * @date 2021/4/18 11:31
     */
    @Test
    public void test01() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(" 表达式无参 无返回值");
            }
        };
        r.run();
        System.out.println("-------------------------------");
        Runnable rLambda = () -> System.out.println("测试   Lambda表达式无参 无返回值");
        rLambda.run();
    }

    /**
     * Lambda表达式 格式二： 有一个参数 无返回值
     *
     * @author xuyayuan
     * @date 2021/4/18 11:31
     */
    @Test
    public void test02() {
        Consumer<String> con = (e) -> System.out.println(e);
        con.accept("Lambda格式二 ： 有一个参数，并且没有返回值");
    }

    /**
     * Lambda表达式 格式三： 若只有有一个参数小括号可以省略不写 无返回值
     *
     * @author xuyayuan
     * @date 2021/4/18 11:31
     */
    @Test
    public void test03() {
        Consumer<String> con = e -> System.out.println(e);
        con.accept("Lambda格式三 ： 若只有有一个参数小括号可以省略不写 无返回值");
    }

    /**
     * Lambda表达式 格式四： 有两个以上的参数，有返回值，并且Lambda体中有多条语句
     *
     * @author xuyayuan
     * @date 2021/4/18 11:31
     */
    @Test
    public void test04() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口用处：有两个以上的参数，有返回值，并且Lambda体中有多条语句");
            return Integer.compare(x, y);
        };
        System.out.println("x 是否大于 y :" + (com.compare(2, 7) > 0 ? true : false));
    }

    /**
     * Lambda表达式 格式五： 若Lambda体中只有一条语句，return 和大括号都可以省略不写
     *
     * @author xuyayuan
     * @date 2021/4/18 11:31
     */
    @Test
    public void test05() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println("x 是否大于 y :" + (com.compare(2, 7) > 0 ? true : false));
    }

    /**
     * 对一个数进行运算 使用Lambda表达式的方式
     * 有一个参数，并且只有一个语句体，return 和 大括号可以省略不写
     * Integer t = operation(100, (e) -> {
     * return e * e;
     * }); 可以简化为 Integer t = operation(100, (e) -> e * e);
     * 步骤：
     * 一：首先提供一个函数式接口 MyFun01
     * 二：提供一个公共方法 operation、
     * 三：书写具体的Lambda表达式
     * 例如平方运算  Integer t = operation(100, (e) -> e * e);
     * 例如加法运算  Integer y = operation(200, (h)-> h+1000);
     *
     * @author jitwxs
     * @date 2021/4/18 12:13
     */
    @Test
    public void test10() {
        Integer t = operation(100, (e) -> {
            return e * e;
        });
        System.out.println("平方运算t的值为:" + t);
        Integer y = operation(200, (h) -> h + 1000);
        System.out.println("加法运算：" + y);
    }

    /**
     * 公共的方法使用多种运算
     * @author xuyayuan
     * @date 2021/4/18 12:21
     * @param num
     * @param m
     * @return java.lang.Integer
     */
    public Integer operation(Integer num, MyFun01 m) {
        return m.getValue(num);
    }

    List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 20, new BigDecimal(999.99)),
            new Employee("李四", 20, new BigDecimal(888.88)),
            new Employee("王五", 60, new BigDecimal(777.77)),
            new Employee("赵六", 80, new BigDecimal(222.22)),
            new Employee("六六", 40, new BigDecimal(99.99))
    );
    /**
     * 调用Collections.sort()方法，通过定制排序比较两个 Employee
     * (先按年龄比，年龄相同 按姓名比)，使用Lambda作为参数传递。
     * @author jitwxs
     * @date 2021/4/18 12:27
     */
    @Test
    public void test11(){
        Collections.sort(employeeList, (x, y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getName().compareTo(y.getName());
            } else {
                return x.getAge().compareTo(y.getAge());
            }
        });
        for (Employee employee :employeeList){
            System.out.println(employee);
        }
    }


}
