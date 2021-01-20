package com.xzh.customer.technical.designPatterns.commandPattern;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-20 16:59
 * @description：接收命令，并执行命令。
 * @modified By：xzh
 * @version: V1.0.0
 */
public class Invoker {
    private MyCommand command;

    //接受命令
    public void setCommand(MyCommand command) {
        this.command = command;
    }

    //执行命令
    public void executeCommand() {
        command.execute();
    }
}
