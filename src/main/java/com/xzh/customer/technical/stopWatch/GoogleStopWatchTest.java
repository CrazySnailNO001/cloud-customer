package com.xzh.customer.technical.stopWatch;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

import static com.google.common.base.Stopwatch.createStarted;

/**
 * @author ：xzh
 * @date ：Created in 2020-06-01 14:47
 * @description：
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
