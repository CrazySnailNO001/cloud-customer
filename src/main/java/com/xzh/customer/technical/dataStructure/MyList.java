package com.xzh.customer.technical.dataStructure;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-04-23 16:30
 * @description：
 * @modified By：
 * @version:
 */
public class MyList {

    /**
     * 安全的删除元素
     */
    private static void safeDelete() {
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeIf(value -> value == 1);
        list.forEach(System.out::println);
    }

    /**
     * java.lang.UnsupportedOperationException
     * Arrays.asList返回的是Arrays内部的一个ArrayList,无法进行add操作
     */
    private static void wrongAdd() {
        String[] array = {"a", "b", "ab", "abb", "abc"};
        List<String> strings = Arrays.asList(array);
        strings.forEach(System.out::println);

        strings.add("444");
    }

    /**
     * 不管我们是修改原数组，还是新 List 集合，两者都会互相影响。
     * 查看 java.util.Arrays$ArrayList 实现，我们可以发现底层实际使用了原始数组。
     */
    private static void doubleUpdate() {
        String[] array = {"a", "b", "ab", "abb", "abc"};
        List<String> strings = Arrays.asList(array);
        System.out.println("List before update : " + strings);

        strings.set(0, "modify list");
        array[2] = "modify arrays";
        System.out.println("Arrays after update : " + Arrays.toString(array));
        System.out.println("List after update : " + strings);
    }

    /**
     * 通过下面两种方式，我们将新的 List 集合与原始数组解耦，不再互相影响，
     * 由于此时还是真正的 ArrayList，不用担心 add/remove报错了。
     */
    private static void singleUpdate() {
        String[] array = {"a", "b", "ab", "abb", "abc"};
//        List<String> list = new ArrayList<>(Arrays.asList(array));
        List<String> list = Lists.newArrayList(array);

        list.set(0, "modify list");
        array[2] = "modify arrays";
        System.out.println("Arrays after update : " + Arrays.toString(array));
        System.out.println("List after update : " + list);
    }

    /**
     * 除了 Arrays#asList产生新集合与原始数组互相影响之外，
     * JDK 另一个方法 List#subList 生成新集合也会与原始 List 互相影响。并且无法进行add操作
     */
    private static void doubleUpdateList() {
        List<Integer> oldList = new ArrayList<>();
        oldList.add(1);
        oldList.add(2);
        oldList.add(3);

        List<Integer> newList = oldList.subList(0, 3);

        newList.set(0, 6);
        oldList.set(1, 7);

        newList.add(4);
        oldList.remove(0); //ConcurrentModificationException
        oldList.add(5); //java.util.ConcurrentModificationException

        System.out.println(oldList); //[7, 3, 4, 5] lodList 修改、新增、删除都成功了
        System.out.println(newList);
    }

    public static void main(String[] args) {
//        safeDelete();

//        wrongAdd();
//        doubleUpdate();
//        singleUpdate();
        doubleUpdateList();

    }

}
