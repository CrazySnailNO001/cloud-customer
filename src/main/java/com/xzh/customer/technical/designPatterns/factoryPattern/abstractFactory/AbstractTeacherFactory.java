package com.xzh.customer.technical.designPatterns.factoryPattern.abstractFactory;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-28 11:47
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class AbstractTeacherFactory implements AbstractFactory {
    @Override
    public People produce() {
        return new Teacher();
    }
}
