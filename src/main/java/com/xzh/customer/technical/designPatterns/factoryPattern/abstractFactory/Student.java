package com.xzh.customer.technical.designPatterns.factoryPattern.abstractFactory;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-27 17:03
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class Student implements People {
    @Override
    public void work() {
        System.out.println("学习...");
    }
}
