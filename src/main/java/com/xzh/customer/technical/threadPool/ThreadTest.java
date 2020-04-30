package com.xzh.customer.technical.threadPool;

/**
 * @author ：xzh
 * @date ：Created in 2020-04-30 10:39
 * @description：
 * @modified By：
 * @version:
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("runnable~");
        };
        Thread thread02 = new Thread(runnable);
        Thread thread = new Thread(() -> System.out.println("================THREAD--running"));


        test001(thread02);
//        test002(thread);
//        test003(thread);
    }

    public static void test001(Thread thread){
        System.out.println(thread.getName()+" : "+thread.getState());  //NEW
        thread.start();
        System.out.println(thread.getName()+" : "+thread.getState()); //RUNNABLE
        thread.run();
        System.out.println(thread.getName()+" : "+thread.getState()); //TERMINATED
    }

    public static void test002(Thread thread){
        System.out.println(thread.getName()+" : "+thread.getState());  //NEW
        thread.run();
        System.out.println(thread.getName()+" : "+thread.getState()); //NEW
        thread.start();
        System.out.println(thread.getName()+" : "+thread.getState()); //RUNNABLE
    }

    public static void test003(Thread thread) throws InterruptedException {
        thread.start();
        System.out.println(thread.getName()+" : "+thread.getState());  //RUNNABLE
        thread.interrupt();
        System.out.println(thread.getName()+" : "+thread.getState()); //TERMINATED
        Thread thread1 = new Thread(() -> {
            System.out.println("=====001 thread");
        });
        thread1.join();
        System.out.println(thread1.getName()+" : "+thread1.getState()); //NEW
        thread1.start();
        System.out.println(thread1.getName()+" : "+thread1.getState()); //RUNNABLE
    }
}
