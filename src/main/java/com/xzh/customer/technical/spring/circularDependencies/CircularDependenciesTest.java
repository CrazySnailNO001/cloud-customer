package com.xzh.customer.technical.spring.circularDependencies;

import org.springframework.context.annotation.Scope;

/**
 * @author ：xzh
 * @date ：Created in 2020-05-04 14:32
 * @description：
 * @modified By：
 * @version:
 */
public class CircularDependenciesTest {
//    private static ClassA classA;

    public static void main(String[] args) {
        ClassA classA = new ClassA();

        System.out.println(classA.toString());
    }
}
