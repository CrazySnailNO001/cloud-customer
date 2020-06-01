package com.xzh.customer.technical.stopWatch;

import org.springframework.util.StopWatch;

/**
 * @author ：xzh
 * @date ：Created in 2020-06-01 14:32
 * @description：
 * StopWatch '计时器': running time (millis) = 508
 * -----------------------------------------
 * ms     %     Task name
 * -----------------------------------------
 * 00205  040%  任务一
 * 00303  060%  任务二
 *
 * 508
 * ===================
 * StopWatch '计时器': running time (millis) = 508
 * -----------------------------------------
 * ms     %     Task name
 * -----------------------------------------
 * 00205  040%  任务一
 * 00303  060%  任务二
 *
 * 508
 * @modified By：
 * @version:
 */
public class StopWatchTest {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch("计时器");
        stopWatch.start("任务一");
        Thread.sleep(200);
        stopWatch.stop();

        stopWatch.start("任务二");
        Thread.sleep(300);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.getTotalTimeMillis());

        System.out.println("===================");

        /**
         * 任务三打印不出来?
         */
//        stopWatch.stop(); //报错:Can't stop StopWatch: it's not running
        stopWatch.start("任务三");
        Thread.sleep(60);
        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.getTotalTimeMillis());

    }
}
