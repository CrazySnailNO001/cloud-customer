package com.xzh.customer.technical.safeAndConcurrent.synchonized;

/**
 * @author ：xzh
 * @date ：Created in 2020-09-24 16:14
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
class SyncTestDto {
    private final Object object = new Object();

    synchronized void syncMethod1() {
        System.out.println("执行同步方法1 start...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行同步方法1 end...");
    }


    synchronized void syncMethod2() {
        System.out.println("执行同步方法2...");
    }

    void synchronizingCode1() {
        synchronized (this) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行同步代码块1...");
        }
    }

    void synchronizingCode2() {
        synchronized (this) {
            System.out.println("执行同步代码块2...");
        }
    }

    void synchronizingCode3() {
        synchronized (this) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行同步代码块3...");
        }
    }

    void synchronizingCode4() {
        synchronized (object) {
            System.out.println("执行同步代码块4...");
        }
    }
}
