package com.aliwo.stream;/**
 * package_name:com.aliwo.stream
 *
 * @author:徐亚远 Date:2021/4/17 22:26
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/

import java.math.BigDecimal;

/**
 * 使用策略模式，获取当前员工中工资大于500的员工信息
 * @author xuyayuan
 * @data 2021年04月17日
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee r) {
        return r.getSalary().compareTo(new BigDecimal(500)) > 0;
    }
}
