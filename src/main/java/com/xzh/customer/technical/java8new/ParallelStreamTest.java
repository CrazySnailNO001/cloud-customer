package com.xzh.customer.technical.java8new;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2020-05-11 17:22
 * @description：
 * @modified By：
 * @version:
 */
public class ParallelStreamTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(1, 10).forEach(list::add);


        streamPrint(list);
        parallelStreamPrint(list);
    }

    /**
     * create by: xzh
     * description: 串行执行
     * create time: 2020-05-11 17:30
     */
    private static void streamPrint(List<Integer> list) {
        list.forEach(value->{
            System.out.println("streamPrint ====   " + Thread.currentThread().getName() + "====   value : " + value);
        });
    }

    /**
     * create by: xzh
     * description: 并行执行
     * create time: 2020-05-11 17:30
     */
    private static void parallelStreamPrint(List<Integer> list) {
        list.parallelStream().forEach(value -> {
            System.out.println("parallelStreamPrint ====   " + Thread.currentThread().getName() + "====   value : " + value);
        });
    }
}
