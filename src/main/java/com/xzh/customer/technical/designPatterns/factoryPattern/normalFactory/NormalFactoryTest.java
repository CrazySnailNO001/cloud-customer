package com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-27 16:50
 * @description：
 * 普通工厂模式，就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。
 * @modified By：xzh
 * @version: V1.0.0
 */
public class NormalFactoryTest {
    public static void main(String[] args) {
        NormalFactory factory = new NormalFactory();
        NormalSender messageSender = factory.produce("message");
        messageSender.send();

        NormalSender mailSender = factory.produce("mail");
        mailSender.send();

        NormalSender badSender = factory.produce("bad");
        badSender.send();
    }
}
