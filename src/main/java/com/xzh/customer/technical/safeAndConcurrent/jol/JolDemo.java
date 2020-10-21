package com.xzh.customer.technical.safeAndConcurrent.jol;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author ：xzh
 * @date ：Created in 2020-10-21 11:30
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Slf4j
public class JolDemo {
    public static void main(String[] args) {
        Object o = new Object();
        test01(o);
        new Thread(() -> {
            synchronized (o){
                test01(o);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private static void test01(Object o){
        print("对象内部信息", ClassLayout.parseInstance(o).toPrintable());
//        print("对象外部信息", GraphLayout.parseInstance(o).toPrintable());
//        print("对象总大小", String.valueOf(GraphLayout.parseInstance(o).totalSize()));
    }

    private static void print(String header, String message) {
        log.info("{}: {}", header, message);
        log.info("===========================");
    }
}
