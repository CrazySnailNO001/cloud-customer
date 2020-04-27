package com.xzh.customer.technical.java8new;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/30 0030 14:43
 * @modify By:
 **/

public class StreamTest {
    public static void main(String[] args) {
        //打印1-9
        IntStream.range(1,10).forEach(value -> {
            System.out.println("IntStream: "+value);
        });

        List list = new ArrayList();
        for (int i = 1; i <= 7; i++) {
            list.add(i);
        }
        list.stream().filter(param -> (int) param % 2 == 1).forEach(System.out::print);  //过滤偶数

        list.clear();
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(5);
        list.add(4);

        System.out.println("");
        list.stream().sorted().forEach(System.out::print);  //升序

        System.out.println("");
        list.stream().sorted((param1,param2) -> ((int)param1 < (int)param2 ? 1:-1)).forEach(System.out::print);//降序

        list.add(1);
        list.add(1);
        list.add(2);
        list.add(6);
        System.out.println("");
        list.stream().distinct().forEach(System.out::print);//去重

        System.out.println("");
        System.out.println(list.stream().reduce((p1, p2) -> (int) p1 * (int) p2).get());//组合

        List list1 = (List) list.stream().filter(param -> (int) param % 2 == 0).collect(Collectors.toList());
        list1.stream().filter(p -> (int) p == 4).forEach(System.out::print);
        System.out.println("");
        list1.forEach(p-> System.out.print(p));
    }
}
