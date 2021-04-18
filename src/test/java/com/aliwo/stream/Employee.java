package com.aliwo.stream;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xuyayuan
 * @data 2021年04月17日
 */
@Data
public class Employee {

    private String name;
    private Integer age;
    private BigDecimal salary;

    public Employee() {
    }
    public Employee(Integer age) {
        this.age = age;
    }

    public Employee(String name, Integer age, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

}
