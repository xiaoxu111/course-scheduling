package com.aliwo.stream.lambda;

/**
 * @author xuyayuan
 * @data 2021年04月18日
 */
@FunctionalInterface
public interface Func02<T,R> {
    R getValue(T t1, T t2);
}
