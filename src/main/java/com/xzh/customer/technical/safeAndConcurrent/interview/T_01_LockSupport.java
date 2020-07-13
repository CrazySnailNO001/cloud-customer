package com.xzh.customer.technical.safeAndConcurrent.interview;

import java.util.concurrent.locks.LockSupport;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-13 22:38
 * @description：
 * @modified By：
 * @version:
 */
public class T_01_LockSupport {
    private static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = "123456".toCharArray();
        char[] aC = "ABCDEF".toCharArray();

        t1 = new Thread(() -> {
            for (char c : aI) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "Thread-01");

        t2 = new Thread(() -> {
            for (char c : aC) {
                LockSupport.park(); //防止t2先执行:如果t2先执行,这里会阻塞住,如果t1调用了LockSupport.unpark(t2);方法,这里就阻塞不住了
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        }, "Thread-02");
        t1.start();
        t2.start();
    }
}
