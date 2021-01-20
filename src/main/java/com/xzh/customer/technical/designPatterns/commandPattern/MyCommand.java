package com.xzh.customer.technical.designPatterns.commandPattern;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-20 09:52
 * @description：用来声明执行操作的接口/抽象类。
 * @modified By：xzh
 * @version: V1.0.0
 */
public abstract class MyCommand {
    protected Receiver receiver;

    public MyCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    //执行命令的方法
    abstract public void execute();
}
