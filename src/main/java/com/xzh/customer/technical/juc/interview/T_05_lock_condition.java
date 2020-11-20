package com.xzh.customer.technical.juc.interview;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-13 23:38
 * @description：
 * @modified By：
 * @version:
 */
public class T_05_lock_condition {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);//保证 先打印数字

    public static void main(String[] args) {
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            countDownLatch.countDown();
            try {
                lock.lock();
                for (char c : aI) {
                    System.out.print(c);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread-01").start();

        new Thread(() -> {
            try {
                countDownLatch.await();
                lock.lock();
                for (char c : aC) {
                    System.out.print(c);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread-02").start();
    }
}
