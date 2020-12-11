package com.xzh.customer.technical.jvm.classLoader;

import java.lang.reflect.InvocationTargetException;
import java.time.temporal.ValueRange;

/**
 * @author ：xzh
 * @date ：Created in 2020-12-11 14:11
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader mcl = new MyClassLoader();
        Class<?> aClass = mcl.loadClass("com.xzh.customer.technical.jvm.classLoader.ClassLoaderPeople", true);
        Object o = aClass.getDeclaredConstructor().newInstance();
        System.out.println(o);
        System.out.println(o.getClass().getClassLoader());

//        Class.forName有一个三个参数的重载方法，可以指定类加载器，平时我们使用的Class.forName("XX.XX.XXX")都是使用的系统类加载器Application ClassLoader。
//        Class<?> c1 = Class.forName("com.xzh.customer.technical.jvm.classLoader.ClassLoaderPeople", true, mcl);
//        Object obj = c1.getDeclaredConstructor().newInstance();
//        System.out.println(obj);
//        System.out.println(obj.getClass().getClassLoader());
    }
}
