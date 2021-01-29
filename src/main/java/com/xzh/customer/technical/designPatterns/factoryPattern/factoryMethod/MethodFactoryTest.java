package com.xzh.customer.technical.designPatterns.factoryPattern.factoryMethod;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-28 11:48
 * @description：工厂方法模式
 * 其实这个模式的好处就是，如果你现在想增加一个功能：发及时信息，则只需做一个实现类，实现Sender接口，同时做一个工厂类，
 * 实现Provider接口，就OK了，无需去改动现成的代码。这样做，拓展性较好！
 * @modified By：xzh
 * @version: V1.0.0
 */
public class MethodFactoryTest {
    public static void main(String[] args) {
        MethodFactory studentFactory = new MethodStudentFactory();
        People student = studentFactory.produce();
        student.work();
        MethodFactory teacherFactory = new MethodTeacherFactory();
        People teacher = teacherFactory.produce();
        teacher.work();
    }
}
