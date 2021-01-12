package com.xzh.customer.technical.designPatterns.singleton;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-23 19:44
 * @description：这种写法简单，在类装载的时候就完成了实例化，避免了线程同步问题，缺点是，没有达到懒加载的效果，如果从始至终都没有使用过这个实力，就会造成内存的浪费。
 * @modified By：
 * @version:
 */
public class HungrySingleton {
    private static HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}
