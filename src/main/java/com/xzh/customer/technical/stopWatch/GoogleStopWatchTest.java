package com.xzh.customer.technical.stopWatch;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

import static com.google.common.base.Stopwatch.createStarted;

/**
 * @author ：xzh
 * @date ：Created in 2020-06-01 14:47
 * @description：Stopwatch使用了操作系统和硬件提供的最高分辨率机制，通常少于1毫秒（相比之下DateTime.Now和Environment.TickCount的分辨率在15毫秒左右）
 * 53
 * 105
 * @modified By：
 * @version:
 */
public class GoogleStopWatchTest {
    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = createStarted();
//        Stopwatch stopwatch = createUnstarted();
//        stopwatch.start();


        Thread.sleep(50);
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));

        stopwatch.stop();
        stopwatch.start();

        Thread.sleep(50);
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
