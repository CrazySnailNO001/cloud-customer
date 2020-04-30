package com.xzh.customer.technical.java8new;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/30 0030 15:30
 * @modify By:
 **/
public class LambdaTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(4);
        list.add(1);

        List<String> stringList = new ArrayList<>();
        stringList.add("na");
        stringList.add("ac");
        stringList.add("ad");
        stringList.add("aee");
        stringList.add("ab");
        stringList.add("ab");

//        print();
//        sortAsc(list);
//        sortDesc(list);
//        sortAscNew(list);
//        sortDescNew(list);
//        sortAscString(stringList);
//        filterString(stringList);
        distinctString(stringList);
    }

    public int add(int x, int y) {
        return x + y;
    }

    /**
     * 打印1-9
     */
    public static void print() {
        IntStream.range(1, 10).forEach(System.out::println);
    }

    /**
     * 升序排序,排的是初始集合
     */
    public static void sortAsc(List<Integer> list) {
        list.sort(Integer::compareTo);
        list.forEach(System.out::println);
    }

    /**
     * 降序排序,排的是初始集合
     */
    public static void sortDesc(List<Integer> list) {
        list.sort((o1, o2) -> o1 < o2 ? 1 : -1);
//        list.stream().sorted((o1, o2) -> o1 < o2 ? 1 : -1).forEach(System.out::println);
        list.forEach(System.out::println);
    }

    /**
     * 升序排序
     * 会生成一个新的集合
     * 初始集合不会改变
     */
    public static void sortAscNew(List<Integer> list) {
        list.stream().sorted(Integer::compareTo).forEach(System.out::println);
//        list.stream().sorted((o1, o2) -> o1 < o2 ? -1 : 1).forEach(System.out::println);

        System.out.println();
        list.forEach(System.out::println);
    }

    /**
     * 降序排序
     * 会生成一个新的集合
     * 初始集合不会改变
     */
    public static void sortDescNew(List<Integer> list) {
        list.stream().sorted((o1, o2) -> o1 < o2 ? 1 : -1).forEach(System.out::println);
        System.out.println();
        list.forEach(System.out::println);
    }

    /**
     * String升序排序,排的是初始集合
     */
    public static void sortAscString(List<String> list) {
        list.sort(String::compareTo);
        list.forEach(System.out::println);
    }

    /**
     * 过滤
     * 过滤的是新的集合,对老集合没影响
     */
    public static void filterString(List<String> list) {
        list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
        System.out.println();
        list.forEach(System.out::println);
    }

    /**
     * 去重
     * 操作的是新的集合,对老集合没影响
     */
    public static void distinctString(List<String> list) {
        list.stream().distinct().forEach(System.out::println);
        System.out.println();
        list.forEach(System.out::println);
    }

}
