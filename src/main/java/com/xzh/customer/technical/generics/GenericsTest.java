package com.xzh.customer.technical.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-05-29 10:27
 * @description： T 是一个 确定的 类型，通常用于泛型类和泛型方法的定义，？是一个 不确定 的类型，通常用于泛型方法的调用代码和形参，不能用于定义类和泛型方法。
 * @modified By：
 * @version:
 */
public class GenericsTest {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        List<Animal> animals = new ArrayList<>();
        animals.add(cat);
        animals.add(dog);
        List<Dog> dogs = new ArrayList<>();
        dogs.add(dog);


        test(animals);
        test001(dogs);
        test002();


    }

    private static void test(List<Animal> animals) {
        System.out.println(count(animals));
        System.out.println(countAnimal(animals));
    }

    private static void test001(List<Dog> dogs) {
        System.out.println(count(dogs));
//        System.out.println(countAnimal(dogs));  //报错
        System.out.println(count03(dogs));
    }

    private static void test002() {
//        List<String> strings = new ArrayList<>();
//        System.out.println(count03(strings));  //报错
    }

    private static int count(List<? extends Animal> animals) {
        return animals.size();
    }

    private static int countAnimal(List<Animal> animals) {
        return animals.size();
    }

    private static <T extends Animal> int count03(List<T> list) {
        return list.size();
    }

//    private static void test(T t){
//
//    }
}
