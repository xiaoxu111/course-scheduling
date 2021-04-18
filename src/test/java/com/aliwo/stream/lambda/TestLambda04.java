package com.aliwo.stream.lambda;

import com.aliwo.stream.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用"方法引用"，(可以理解为方法引用是Lambda表达式的另外一种表现形式)。
 * <p>
 * 主要有三种语法形式：
 * <p>
 * 一：对象::实例方法名
 * <p>
 * 二：类::静态方法名
 * <p>
 * 三：类::实例方法名
 * 注意：
 * Lambda体中调用方法的参数列表和返回值类型，需要与函数式接口中抽象方法的函数列表和返回值类型保持一致。
 *
 * @author xuyayuan
 * @data 2021年04月18日
 */
public class TestLambda04 {
    /**
     * 一：对象::实例方法名
     * 注意：
     * Lambda体中调用方法的参数列表和返回值类型，需要与函数式接口中抽象方法的函数列表和返回值类型保持一致。
     *
     * @author xuyayuan
     * @date 2021/4/18 20:54
     */
    @Test
    public void test01() {
        Consumer<String> con1 = (e) -> System.out.println(e);
        con1.accept("abcde");
        System.out.println("-----对象::实例方法名的格式------");
        Consumer<String> con2 = System.out::println;
        con2.accept("rrttoo");
    }

    /**
     * 一：对象::实例方法名
     * 注意：
     * Lambda体中调用方法的参数列表和返回值类型，需要与函数式接口中抽象方法的函数列表和返回值类型保持一致。
     *
     * @author xuyayuan
     * @date 2021/4/18 20:54
     */
    @Test
    public void test02() {
        Employee employee = new Employee("李四", 30, new BigDecimal(100));
        Supplier<String> stringSupplier = () -> employee.getName();
        System.out.println(stringSupplier.get());
        System.out.println("-------方法引用一： 对象::实例方法名");
        Supplier<Integer> integerSupplier = employee::getAge;
        System.out.println(integerSupplier.get());
    }

    /**
     * 二：类::静态方法名
     *
     * @author xuyayuan
     * @date 2021/4/18 21:09
     */
    @Test
    public void test03() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        Integer i = comparator.compare(1, 2);
        System.out.println("1 是否 大于 2 : " + (i > 0 ? true : false));
        System.out.println("------类::静态方法名的格式------");
        Comparator<Integer> comparator1 = Integer::compare;
        System.out.println("使用 类::静态方法名的格式 进行判断 2 是否 大于 1 :" + (comparator1.compare(2, 1) > 0 ? true : false));
    }

    /**
     * 三：类::实例方法名
     * 若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用类::实例方法名。
     *
     * @author xuyayuan
     * @date 2021/4/18 21:09
     */
    @Test
    public void test04() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        System.out.println("Lambda表达式的形式:" + bp.test("aa", "aa"));
        System.out.println("------类::实例方法名------");
        BiPredicate<String, String> bp2 = String::equals;
        System.out.println("类::实例方法名 :" + bp2.test("cc", "cf"));
    }

    /**
     * 四：构造器引用
     * 格式：ClassName::new
     * 需要调用的构造器的参数列表需要与函数式接口中抽象方法的参数列表保持一致。
     *
     * @author xuyayuan
     * @date 2021/4/18 21:09
     */
    @Test
    public void test05() {
        Supplier<Employee> supplier = () -> new Employee();
        Employee e = supplier.get();
        e.setName("lambda 表达式 供给型");
        System.out.println(e.getName());
        System.out.println("使用构造器引用实现");
        Supplier<Employee> employeeSupplier = Employee::new;
        Employee employee = employeeSupplier.get();
        employee.setAge(12);
        System.out.println(employee.getAge());

        Function<Integer, Employee> fun = (x) -> new Employee(x);

        Function<Integer, Employee> function = Employee::new;
    }

    /**
     * 数组的引用
     * 格式：Type[]::new
     * @author xuyayuan
     * @date 2021/4/18 22:08
     */
    @Test
    public void test06(){
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] str = fun.apply(10);
        System.out.println(str.length);
        System.out.println("数组引用的方式实现");
        Function<Integer, String[]> fuc = String[]::new;
        String [] strings = fuc.apply(20);
        System.out.println("数组引用的方式实现:" + strings.length);
    }


}
