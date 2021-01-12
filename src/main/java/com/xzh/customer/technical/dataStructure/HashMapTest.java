package com.xzh.customer.technical.dataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2020-12-29 16:48
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        IntStream.range(0, 20).forEach(value -> {
            map.put("key" + value, value);
        });
    }
}
