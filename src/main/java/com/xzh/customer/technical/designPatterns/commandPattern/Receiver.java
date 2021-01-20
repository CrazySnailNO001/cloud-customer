package com.xzh.customer.technical.designPatterns.commandPattern;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-20 09:53
 * @description：该角色就是干活的角色，命令传递到这里是应该被执行的。
 * @modified By：xzh
 * @version: V1.0.0
 */
public class Receiver {
    public void doAction() {
        System.out.println("执行请求！");
    }
}
