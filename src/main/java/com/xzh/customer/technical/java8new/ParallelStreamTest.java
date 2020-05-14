package com.xzh.customer.technical.java8new;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2020-05-11 17:22
 * @description： 默认线程数 : CPU核心数
 */
public class ParallelStreamTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        IntStream.range(1, 20).forEach(list::add);

        System.out.println("CPU处理器的数量: " + Runtime.getRuntime().availableProcessors());
        System.out.println("parallelStream默认的并发线程数: " + ForkJoinPool.getCommonPoolParallelism());
//        streamPrint(list);
//        parallelStreamPrint(list);

//        testThread(list);
        resetThread(list);

    }

    /**
     * create by: xzh
     * description: 串行执行
     * create time: 2020-05-11 17:30
     */
    private static void streamPrint(List<Integer> list) {
        list.stream().parallel().forEach(value ->
                System.out.println("streamPrint >>  " + Thread.currentThread().getName() + "  >>> : " + value + "  thread active count >>  " + Thread.activeCount()));
    }

    /**
     * create by: xzh
     * description: 并行执行
     * create time: 2020-05-11 17:30
     */
    private static void parallelStreamPrint(List<Integer> list) {
        list.parallelStream().forEach(value -> System.out.println("parallelStreamPrint >>  " + Thread.currentThread().getName() + ">>> : " + value));
    }

    private static void testThread(List<Integer> list) {
        Thread thread01 = new Thread(() -> test001(list));
        Thread thread02 = new Thread(() -> test002(list));
        thread01.start();
        thread02.start();
        try {
            thread01.join();
            thread02.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void test001(List<Integer> list) {
        list.parallelStream().forEach(value -> {
            System.out.println("parallelStreamPrint001 >>  " + Thread.currentThread().getName() + "  >>> : " + value + "  thread active count >>  " + Thread.activeCount());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void test002(List<Integer> list) {
        list.parallelStream().forEach(value -> {
            System.out.println("parallelStreamPrint002 >>  " + Thread.currentThread().getName() + "  >>> : " + value + "  thread active count >>  " + Thread.activeCount());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * create by: xzh
     * description:  默认情况下,真正执行的线程数=cup核数(8);但是Thread.activeCount()=真正执行线程数+2(10)
     * 修改成15 之后,真正执行的线程数=15;但是Thread.activeCount()=真正执行线程数+2(17)
     */
    private static void resetThread(List<Integer> list) {

        //修改ForkJoinPool线程数方法一(修改系统的ForkJoinPool线程池大小) 不好使???
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
//        test001(list);


        //修改ForkJoinPool线程数方法二
        ForkJoinTask<?> submit = new ForkJoinPool(20).submit(() -> test001(list));
        try {
            Object o = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("parallelStream默认的并发线程数: " + ForkJoinPool.getCommonPoolParallelism());


    }
}
