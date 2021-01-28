package com.xzh.customer.technical.designPatterns.factoryPattern.staticFactory;

import com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory.NormalSender;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-27 16:59
 * @description：
 * 静态工厂方法模式，将上面的多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可。
 * @modified By：xzh
 * @version: V1.0.0
 */
public class StaticFactoryTest {
    public static void main(String[] args) {
        NormalSender sender = StaticFactory.produceMail();
        NormalSender sender1 = StaticFactory.produceMessage();
        sender.send();
        sender1.send();
    }
}
