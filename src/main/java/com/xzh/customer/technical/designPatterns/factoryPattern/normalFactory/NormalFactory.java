package com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-27 16:40
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class NormalFactory {
    public NormalSender produce(String name) {
        switch (name) {
            case "mail":
                return new MailNormalSender();
            case "message":
                return new MessageNormalSender();
            default:
                System.out.println("请输入正确的类型...");
                return null;
        }
    }
}
