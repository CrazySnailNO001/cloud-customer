package com.xzh.customer.technical.juc.interview;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-13 23:20
 * @description：
 * @modified By：
 * @version:
 */
public class T_04_sync_wait_notify {
//    private static volatile boolean t1Started = false;//保证 先打印数字

    private static CountDownLatch countDownLatch = new CountDownLatch(1);//保证 先打印数字

    public static void main(String[] args) {
        final Object object = new Object();

        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        new Thread(() -> {
            synchronized (object) {
                for (char c : aI) {
                    System.out.print(c);
//                    t1Started = true;
                    countDownLatch.countDown();

                    try {
                        object.notify();
                        object.wait();  //让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();    //必须这样,不然程序无法退出
            }
        }, "Thread-01").start();


        new Thread(() -> {
            synchronized (object) {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                while (!t1Started) {
//                    try {
//                        object.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

                for (char c : aC) {
                    System.out.print(c);
                    try {
                        object.notify();
                        object.wait();  //让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }, "Thread-02").start();


    }
}
