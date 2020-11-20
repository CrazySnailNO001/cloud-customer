package com.xzh.customer.technical.juc.interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-13 23:05
 * @description：
 * @modified By：
 * @version:
 */
public class T_03_AtomicInteger {
    private static AtomicInteger thread = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        new Thread(() -> {
            for (char c : aI) {
                while (thread.get() != 1) {
                }
                System.out.print(c);
                thread.set(2);
            }
        }, "Thread-01").start();


        new Thread(() -> {
            for (char c : aC) {
                while (thread.get() != 2) {
                }
                System.out.print(c);
                thread.set(1);
            }
        }, "Thread-02").start();

    }
}
