package com.xzh.customer.technical.designPatterns.singleton;

import lombok.Synchronized;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-23 19:53
 * @description：
 * @modified By：
 * @version:
 */
public class LazySingleton {
    private static LazySingleton lazySingleton;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            synchronized (Synchronized.class) {
                lazySingleton = new LazySingleton();
            }
        }
        return lazySingleton;
    }
}
