package com.xzh.customer.technical.juc.threadLocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-04 22:15
 * @description：线程本地变量(属于线程私有的)
 * @modified By：
 * @version:
 */
@Slf4j
public class ThreadLocalTest {

    static class Person {
        String name = "TestName";
    }

    private static ThreadLocal<Person> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("Thread-01 {}", threadLocal.get()); //get不到
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Person person = new Person();
            threadLocal.set(person);  //以threadLocal为key,person为value存到当前线程的ThreadLocalMap中
//            threadLocal.remove();   //如果ThreadLocal对象不使用了,必须调用这个方法,不然会发生内存泄露,因为map中的value无法被回收
        }).start();
    }
/**
 * ThreadLocal set()方法源码
 */
//    public void set(T value) {
//        Thread t = Thread.currentThread();
//        ThreadLocalMap map = getMap(t);  map:ThreadLocal.ThreadLocalMap threadLocals = null;当前线程的一个成员变量
//        if (map != null) {
//            map.set(this, value); 存的时候:key:ThreadLocal对象(调用set方法的ThreadLocal对象)
//              例子中的是:private static ThreadLocal<Person> threadLocal = new ThreadLocal<>();
//        } else {
//            createMap(t, value);
//        }
//    }
//    set的时候,使用了一个弱引用的Entry数组
//    tab[i] = new Entry(key, value);
//
//    static class Entry extends WeakReference<ThreadLocal<?>> {
//            /** The value associated with this ThreadLocal. */
//            Object value;
//
//            Entry(ThreadLocal<?> k, Object v) {
//                super(k); //new WeakReference()<k>
//                value = v;
//            }
//        }
}
