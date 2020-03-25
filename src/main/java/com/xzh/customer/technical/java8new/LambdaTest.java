package com.xzh.customer.technical.java8new;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/30 0030 15:30
 * @modify By:
 **/
public class LambdaTest {
    public static void main(String[] args){
        Object o = (Runnable) () -> { System.out.println("hi"); }; // correct
    }
    public int add(int x, int y) {
        return x + y;
    }

}
