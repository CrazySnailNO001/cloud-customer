package com.xzh.customer.technical.object;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-31 10:43
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/object")
public class ObjectController {
    @GetMapping("/test001")
    public void test001() {
        Object o = new Object();
        boolean equals = o.equals(o);
        int i = o.hashCode();

    }

    public static void main(String[] args) {

//        hashEqualsTest();
        try {
            testGeneric();
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }


    public static void hashEqualsTest(){
        Map<User,Integer> userHashMap = new HashMap<>();
        User user1 = new User("zs",32);

        User user2 = new User("ls",23);
        User user3 = new User("ls",23);
        User user4 = new User("ls",23);
        User user5 = new User("ls",23);
        User user6 = new User("ls",23);
        User user7 = new User("ls",23);
        User user8 = new User("ls",23);
        User user9 = new User("ls",23);
        User user10 = new User("ls",23);

        userHashMap.put(user1,1);
        System.out.println("first time"+userHashMap);
        userHashMap.put(user2,2);
        userHashMap.put(user3,3);
        userHashMap.put(user4,4);
        userHashMap.put(user5,5);
        userHashMap.put(user6,6);
        userHashMap.put(user7,7);
        userHashMap.put(user8,8);
        userHashMap.put(user9,9);
        userHashMap.put(user10,10);
        System.out.println("second time"+userHashMap);


//        HashSet<User> hashSet = new HashSet<>();
//
//        hashSet.add(user1);
//        System.out.println("first time"+hashSet);
//        hashSet.add(user2);
//        System.out.println("second time"+hashSet);
    }


    private static void testGeneric() throws InvocationTargetException, IllegalAccessException {

        List<Integer> list = new ArrayList<>();

        list.add(2);

        Class<? extends List> listClass = list.getClass();

        Method[] declaredMethods = listClass.getDeclaredMethods();
        for (Method method: declaredMethods){
            method.setAccessible(true);
            if (method.getName().equals("add") && method.getParameterCount() == 1){
                System.out.println(method);
                method.invoke(list,"hello world");
            }
        }
        System.out.println(list);


    }


}
