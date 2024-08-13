package com.xzh.customer.exame;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author ：xuzhonghui
 * @Date ： 2024/07/12
 * @Description ：
 */
public class TimeCompute {
    public static void main(String[] args) {
        List<Record> list = Lists.newArrayList();
        list.add(new Record("张三", 1, 3, Instant.parse("2019-07-12T14:35:28.30Z")));
        list.add(new Record("张三", 1, 1, Instant.parse("2019-07-12T14:35:28.10Z")));
        list.add(new Record("张三", 1, 2, Instant.parse("2019-07-12T14:35:28.20Z")));
        list.add(new Record("张三", 2, 2, Instant.parse("2019-07-12T14:35:28.50Z")));
        list.add(new Record("张三", 2, 1, Instant.parse("2019-07-12T14:35:28.40Z")));
        list.add(new Record("张三", 2, 3, Instant.parse("2019-07-12T14:35:28.60Z")));

        // 对list按jieDian分组，再按lunCi排序
        HashMap<Integer, List<Record>> collect = list.stream()
                .collect(Collectors.groupingBy(Record::getJieDian, HashMap::new, Collectors.collectingAndThen(
                        Collectors.toList(),
                        list1 -> list1.stream().sorted(Comparator.comparing(Record::getLunCi)).collect(Collectors.toList()))));

        System.out.println(collect);
    }

    @AllArgsConstructor
    @Data
    static class Record {
        private String name;
        private int jieDian;
        private int lunCi;
        private Instant time;
    }
}
