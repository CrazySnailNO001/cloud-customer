package com.xzh.customer.technical.designPatterns.commandPattern;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-20 17:01
 * @description：首先定义一个接收者，然后定义一个命令用于发送给接收者，之后再声明一个调用者，即可把命令交给调用者执行。
 * @modified By：xzh
 * @version: V1.0.0
 */
public class Client {
    public static void main(String[] args) {
        //定义接收者
        Receiver receiver = new Receiver();
        //定义一个发送给接收者的命令
        MyCommand command = new ConcreteCommand(receiver);
        //声明调用者
        Invoker invoker = new Invoker();

        //把命令交给调用者执行
        invoker.setCommand(command);
        invoker.executeCommand();
    }
}