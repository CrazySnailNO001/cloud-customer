package com.xzh.customer.technical.safeAndConcurrent.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2019-11-13 14:57
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class MineForkJoinPool {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList();
        Map<Integer, Integer> map = new ConcurrentHashMap();
        IntStream.range(0, 10000).forEach(list1::add);
        System.out.println("======list1======" + list1.size());

        long l1 = Instant.now().getNano();
        list1.stream().forEach(integer -> {
            map.put(integer, integer);
        });
        long l2 = Instant.now().getNano();
        System.out.println(l2 - l1);


        long l3 = Instant.now().getNano();
        ForkJoinPool forkJoinPool1 = new ForkJoinPool(20);
        forkJoinPool1.submit(() -> {
            list1.parallelStream().forEach(integer -> {
                log.info(Thread.currentThread().getName());
                map.put(integer, integer);
            });
        });

        long l4 = Instant.now().getNano();
        System.out.println(l4 - l3);
    }
}
