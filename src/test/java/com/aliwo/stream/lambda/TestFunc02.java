package com.aliwo.stream.lambda;

import org.junit.Test;

/**
 * @author xuyayuan
 * @data 2021年04月18日
 */
public class TestFunc02 {

    @Test
    public void test01(){
         operation(2L, 3L, (x, y) -> x + y);
         operation(2L, 3L, (x, y) -> x * y);
    }
    public void operation(Long t1, Long t2, Func02<Long, Long> func02){
        System.out.println(func02.getValue(t1, t2));
    }
}
