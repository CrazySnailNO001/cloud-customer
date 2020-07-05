package com.xzh.customer.technical.safeAndConcurrent.volatileDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-04 16:40
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class VolatileTest {
    private static volatile boolean volatileFlag = true;
    private static boolean generalFlag = true;
    private static boolean onSpinWaitFlag = true;

    void volatileRun() {
        log.info("{} start...", Thread.currentThread().getName());
        while (volatileFlag) {
        }
        log.info("{} end...", Thread.currentThread().getName());
    }

    void generalRun() {
        log.info("{} start...", Thread.currentThread().getName());
        while (generalFlag) {
        }
        log.info("{} end...", Thread.currentThread().getName());
    }

    void onSpinWaitRun() {
        log.info("{} start...", Thread.currentThread().getName());
        while (generalFlag) {
            Thread.onSpinWait(); //也可以实现可见性,Thread.sleep(0);也可以(线程会从运行态到等待态,再到就绪态,再到运行态,会重新从内核空间读取最新的值)
        }
        log.info("{} end...", Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest volatileTest = new VolatileTest();
        new Thread(volatileTest::volatileRun, "Thread-volatile").start();
        new Thread(volatileTest::generalRun, "Thread-general").start();
        new Thread(volatileTest::onSpinWaitRun, "Thread-onSpinWait").start();

        TimeUnit.SECONDS.sleep(1);

        volatileTest.volatileFlag = false;
        volatileTest.generalFlag = false;
        volatileTest.onSpinWaitFlag = false;
    }
}
