package com.xzh.customer.technical.designPatterns.factoryPattern.factoryMethod;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-28 11:47
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class MethodStudentFactory implements MethodFactory {
    @Override
    public People produce() {
        return new Student();
    }
}
