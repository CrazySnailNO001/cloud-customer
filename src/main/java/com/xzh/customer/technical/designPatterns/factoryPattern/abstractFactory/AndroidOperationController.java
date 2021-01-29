package com.xzh.customer.technical.designPatterns.factoryPattern.abstractFactory;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-29 11:01
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class AndroidOperationController implements OperationController {
    @Override
    public void control() {
        System.out.println("AndroidOperationController");
    }
}
