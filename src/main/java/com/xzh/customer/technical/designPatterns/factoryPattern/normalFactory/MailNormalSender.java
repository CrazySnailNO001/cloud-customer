package com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-27 16:44
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class MailNormalSender implements NormalSender {
    @Override
    public void send() {
        System.out.println("发送邮件...");
    }
}
