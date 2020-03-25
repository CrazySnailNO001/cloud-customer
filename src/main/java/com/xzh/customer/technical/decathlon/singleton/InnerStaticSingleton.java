package com.xzh.customer.technical.decathlon.singleton;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-24 22:10
 * @description：静态内部类
 * @modified By：
 * @version:
 */
public class InnerStaticSingleton {
    private InnerStaticSingleton() {
    }

    public static InnerStaticSingleton getInstance() {
        return InnerStaticSingletonInstance.innerStaticSingleton;
    }

    private static class InnerStaticSingletonInstance {
        private static final InnerStaticSingleton innerStaticSingleton = new InnerStaticSingleton();
    }
}
