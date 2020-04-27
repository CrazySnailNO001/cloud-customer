package com.xzh.customer.technical.java8new;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/30 0030 15:30
 * @modify By:
 **/
public class LambdaTest {
    public static void main(String[] args) {
        Object o = (Runnable) () -> {
            System.out.println("hi");
        }; // correct

        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.forEach(System.out::println);
    }

    public int add(int x, int y) {
        return x + y;
    }

}
