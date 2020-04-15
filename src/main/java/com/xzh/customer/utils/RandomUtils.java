package com.xzh.customer.utils;

import lombok.Synchronized;

import java.util.Random;

/**
 * @author ：xzh
 * @date ：Created in 2020-04-15 10:27
 * @description：
 * @modified By：
 * @version:
 */
public class RandomUtils {
    private static volatile RandomUtils randomUtils;

    private RandomUtils() {
    }

    public static RandomUtils getInstance() {
        if (null == randomUtils)
            synchronized (Synchronized.class) {
                if (null == randomUtils)
                    return new RandomUtils();
            }
        return randomUtils;
    }

    private Random random = new Random();

    /**
     * 随机生成min和max之间一个数，包括min不包括max
     *
     * @param min
     * @param max
     * @return [min, max)
     */
    public int generateValue(int min, int max) {
        return (int) (random.nextDouble() * (max - min) + min);
    }

}
