package com.xzh.customer.technical.juc.countDownLatch;

import com.xzh.customer.technical.juc.aqs.AqsLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-05 15:48
 * @description：
 * @modified By：
 * @version:
 */
public class CountDownLatchTest {
    private static int m = 0;
    private static Lock lock = new AqsLock();
    private static CountDownLatch countDownLatch = new CountDownLatch(100);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        IntStream.range(0, 100).forEach(value -> threads[value] = new Thread(() -> {
            try {
                lock.lock();
                IntStream.range(0, 100).forEach(value1 -> m++);
            } finally {
                lock.unlock();
            }
            countDownLatch.countDown();
        }));

        for (Thread thread : threads) thread.start();

        countDownLatch.await();
        System.out.println(m);
    }
}
