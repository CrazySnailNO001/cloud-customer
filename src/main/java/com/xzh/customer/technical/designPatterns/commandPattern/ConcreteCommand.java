package com.xzh.customer.technical.designPatterns.commandPattern;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-20 09:54
 * @description：具体的Command类，用于构造传递接收者，根据环境需求，具体的命令类也可能有n个。
 * @modified By：xzh
 * @version: V1.0.0
 */
public class ConcreteCommand extends MyCommand {
    //构造传递接收者
    public ConcreteCommand(Receiver receiver) {
        super(receiver);
    }

    //必须实现一个命令
    @Override
    public void execute() {
        receiver.doAction();
    }
}
