package com.xzh.customer.decathlon.singleton;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-23 19:50
 * @description：
 * @modified By：
 * @version:
 */
public class HungryStaticCodeSingleton {
    private static HungryStaticCodeSingleton hungryStaticCodeSingleton;

    static {
        hungryStaticCodeSingleton = new HungryStaticCodeSingleton();
    }

    private HungryStaticCodeSingleton() {
    }

    public static HungryStaticCodeSingleton getInstance() {
        return hungryStaticCodeSingleton;
    }
}
