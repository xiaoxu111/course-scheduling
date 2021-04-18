package com.aliwo.stream;

/**
 * 使用策略模式，获取当前员工中年龄大于35的员工信息
 * @author xuyayuan
 * @data 2021年04月17日
 */
public class FilterEmployeeByAge implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee r) {
        return r.getAge() > 35;
    }
}
