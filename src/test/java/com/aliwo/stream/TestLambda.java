package com.aliwo.stream;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * Lambda表达式的学习
 *
 * @author xuyayuan
 * @data 2021年04月17日
 */
public class TestLambda {
    /**
     * 原来的匿名内部类
     *
     * @author xuyayuan
     * @date 2021/4/17 21:14
     */
    @Test
    public void test01() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    /**
     * Lambda表达式的方式实现
     * @author xuyayuan
     * @date 2021/4/17 21:13
     */
    @Test
    public void test02() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 20, new BigDecimal(999.99)),
            new Employee("李四", 30, new BigDecimal(888.88)),
            new Employee("王五", 60, new BigDecimal(777.77)),
            new Employee("赵六", 80, new BigDecimal(222.22)),
            new Employee("六六", 40, new BigDecimal(99.99))
    );


    /**
     * 测试获取当前员工中年龄大于35的员工信息
     * @author xuyayuan
     * @date 2021/4/17 21:35
     */
    @Test
    public void test03(){
        List<Employee> list = filterByAge(employeeList);
        for (Employee employee : list){
            System.out.println(employee);
        }
    }
    /**
     * 需求，获取当前员工中年龄大于35的员工信息
     * @author jitwxs
     * @date 2021/4/17 21:27
     * @param employeeList
     * @return null
     */
    public List<Employee> filterByAge(List<Employee> employeeList){
        List<Employee> newList = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getAge() > 35){
                    newList.add(employee);
            }
        }
        return newList;
    }


    /**
     * 测试，获取当前员工中工资大于500的员工信息
     * @author xuyayuan
     * @date 2021/4/17 21:35
     */
    @Test
    public void test04(){
        List<Employee> list = filterBySalary(employeeList);
        for (Employee employee : list){
            System.out.println(employee);
        }
    }
    /**
     * 需求，获取当前员工中工资大于500的员工信息
     * @author jitwxs
     * @date 2021/4/17 21:27
     * @param employeeList
     * @return null
     */
    public List<Employee> filterBySalary(List<Employee> employeeList){
        List<Employee> salaryList = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee.getSalary().compareTo(new BigDecimal(500)) > 0){
                salaryList.add(employee);
            }
        }
        return salaryList;
    }



    /**
     * 优化一，使用策略模式
     * 测试，获取当前员工中年龄大于35的员工信息
     * @author xuyayuan
     * @date 2021/4/17 21:35
     */
    @Test
    public void test05(){
        List<Employee> list = filterEmployee(employeeList, new FilterEmployeeByAge());
        for (Employee employee : list){
            System.out.println(employee);
        }
    }

    /**
     * 优化一，使用策略模式
     *
     * @author xuyayuan
     * @date 2021/4/17 22:25
     * @param list
     * @param mp 
     * @return java.util.List<com.aliwo.stream.Employee>
     */
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp){
        List<Employee> ageList = new ArrayList<>();
        for (Employee employee : list){
            if (mp.test(employee)) ageList.add(employee);
        }
        return ageList;
    }

    /**
     * 优化一，使用策略模式
     * 测试，获取当前员工中工资大于500的员工信息
     * @author xuyayuan
     * @date 2021/4/17 21:35
     */
    @Test
    public void test06(){
        List<Employee> list = filterEmployee(employeeList, new FilterEmployeeBySalary());
        for (Employee employee : list){
            System.out.println(employee);
        }
    }

    /**
     * 优化方式二：匿名内部类
     * @author xuyayuan
     * @date 2021/4/17 22:36
     */
    @Test
    public void test07(){
        List<Employee> list = filterEmployee(employeeList, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee r) {
                return r.getAge() > 35;
            }
        });
        for (Employee employee : list){
            System.out.println(employee);
        }
    }

    /**
     * 优化方式三：Lambda表达式
     * 获取当前员工中工资大于500的员工信息
     * @author xuyayuan
     * @date 2021/4/17 22:36
     */
    @Test
    public void test08(){
        List<Employee> list = filterEmployee(employeeList, (e)->e.getSalary().compareTo(new BigDecimal(500)) > 0);
        list.forEach(System.out::println);
    }

    /**
     * 优化方式四 Stream API
     * 获取当前员工中工资大于500的员工信息
     * @author xuyayuan
     * @date 2021/4/17 22:36
     */
    @Test
    public void test09(){
        employeeList.stream()
                    .filter((e) -> e.getSalary().compareTo(new BigDecimal(500)) > 0)
                    .limit(1)
                    .forEach(System.out::println);
        System.out.println("提取所有的名字----------------------------------------");
        employeeList.stream()
                    .map(Employee::getName)
                    .forEach(System.out::println);
    }

    

}
