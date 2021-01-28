package com.xzh.customer.technical.designPatterns.factoryPattern.manyFactory;

import com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory.NormalSender;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-27 16:56
 * @description：
 * 多个工厂方法模式，是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象，
 * 而多个工厂方法模式是提供多个工厂方法，分别创建对象.
 * @modified By：xzh
 * @version: V1.0.0
 */
public class ManyFactoryTest {
    public static void main(String[] args) {
        ManyFactory manyFactory = new ManyFactory();
        NormalSender sender = manyFactory.produceMail();
        NormalSender sender1 = manyFactory.produceMessage();
        sender.send();
        sender1.send();
    }
}
