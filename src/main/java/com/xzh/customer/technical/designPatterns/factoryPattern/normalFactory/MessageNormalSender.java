package com.xzh.customer.technical.designPatterns.factoryPattern.normalFactory;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-27 16:45
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class MessageNormalSender implements NormalSender {
    @Override
    public void send() {
        System.out.println("发送信息...");
    }
}
