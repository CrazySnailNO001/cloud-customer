package com.xzh.customer.technical.designPatterns.singleton;

import lombok.Synchronized;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-23 21:36
 * @description：
 * @modified By：
 * @version:
 */
public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton doubleCheckSingleton;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getInstance() {
        if (doubleCheckSingleton == null) {
            synchronized (Synchronized.class) {
                if (doubleCheckSingleton == null) {
                    doubleCheckSingleton = new DoubleCheckSingleton();
                }
            }
        }
        return doubleCheckSingleton;
    }
}
