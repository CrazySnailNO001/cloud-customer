package com.xzh.customer.technical.juc.synchonized;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-04 23:45
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class SyncTest {
    public static void main(String[] args) {
//        Object o = new Object();
//        System.out.println(ClassLayOut);
        SyncTestDto syncTestDto = new SyncTestDto();

//        test01(syncTestDto);
//        test02(syncTestDto);
//        test03(syncTestDto);
//        test04(syncTestDto);
        test05(syncTestDto);
    }

    /**
     * 同步方法默认用 this 或者当前类 class 对象作为锁
     * syncMethod1执行的时候,syncMethod2拿不到锁,所以阻塞
     * 执行结果
     * 执行同步方法1 start...
     * 执行同步方法1 end...
     * 执行同步方法2...
     */
    private static void test01(SyncTestDto syncTestDto) {
        new Thread(syncTestDto::syncMethod1).start();
        new Thread(syncTestDto::syncMethod2).start();
    }

    /**
     * synchronizingCode1执行的时候,拿到了锁this,synchronizingCode2拿不到锁,所以阻塞
     * 执行结果
     * 执行同步代码块1...
     * 执行同步代码块2...
     */
    private static void test02(SyncTestDto syncTestDto) {
        new Thread(syncTestDto::synchronizingCode1).start();
        new Thread(syncTestDto::synchronizingCode2).start();
    }

    /**
     * synchronizingCode3执行的时候,拿到了锁this,synchronizingCode4拿到锁object,继续执行
     * 执行结果
     * 执行同步代码块4...
     * 执行同步代码块3...
     */
    private static void test03(SyncTestDto syncTestDto) {
        new Thread(syncTestDto::synchronizingCode3).start();
        new Thread(syncTestDto::synchronizingCode4).start();
    }

    /**
     * syncMethod1执行的时候,拿到了锁this,synchronizingCode4拿到锁object,继续执行
     * 执行结果
     * 执行同步方法1 start...
     * 执行同步代码块4...
     * 执行同步方法1 end...
     */
    private static void test04(SyncTestDto syncTestDto) {
        new Thread(syncTestDto::syncMethod1).start();
        new Thread(syncTestDto::synchronizingCode4).start();
    }

    /**
     * 两个方法的锁都是this
     * 执行结果
     * 执行同步方法1 start...
     * 执行同步方法1 end...
     * 执行同步代码块2...
     */
    private static void test05(SyncTestDto syncTestDto) {
        new Thread(syncTestDto::syncMethod1).start();
        new Thread(syncTestDto::synchronizingCode2).start();
    }
}
