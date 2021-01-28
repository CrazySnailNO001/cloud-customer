package com.xzh.customer.technical.designPatterns.factoryPattern.abstractFactory;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-28 11:48
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory studentFactory = new AbstractStudentFactory();
        People student = studentFactory.produce();
        student.work();
        AbstractFactory teacherFactory = new AbstractTeacherFactory();
        People teacher = teacherFactory.produce();
        teacher.work();
    }
}
