package com.xzh.customer.technical.algorithm.sort;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-20 11:12
 * @description： https://www.cnblogs.com/guoyaohua/p/8600214.html
 * @modified By：
 * @version:
 */
public class CustomSort {
    private static final List<String> regulationOrder = Lists.newArrayList("安徽", "北京", "上海", "深圳", "广州");

    public static void main(String[] args) {
        customSort();
    }

    /**
     * create by: xzh
     * description: 根据指定的规则排序
     * create time: 2020-08-20 14:25
     */
    private static void customSort() {
        List<SortStudent> students = new ArrayList<>();
        SortStudent student1 = new SortStudent("Mike", 5, "上海");
        SortStudent student2 = new SortStudent("Mike", 5, "安徽");
        SortStudent student3 = new SortStudent("Mike", 5, "北京");
        SortStudent student4 = new SortStudent("Mike", 4, "深圳");
        SortStudent student5 = new SortStudent("Mike", 4, "安徽");
        SortStudent student6 = new SortStudent("Mike", 6, "南京");
        SortStudent student7 = new SortStudent("Mike", 6, "广州");

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        students.add(student7);

        //根据省份排序
        students.sort((o1, o2) -> {
            int index1 = regulationOrder.indexOf(o1.getProvince());
            if (index1 == -1)
                return 1;
            int index2 = regulationOrder.indexOf(o2.getProvince());
            if (index2 == -1)
                return -1;
            return index1 - index2;
        });

        //注意:低优先级的要先排序,这样高优先级的才不会被打乱,我这里高优先级的是age,低优先级的是province
        //根据年龄降序排序,stream排序不改变原始list顺序
        //结果:先按年龄降序,再按省份特定顺序排序
        List<SortStudent> sortedList = students.stream()
                .sorted(Comparator.comparing(SortStudent::getAge).reversed()).collect(Collectors.toList());

        for (int i = 0; i < students.size(); i++) {
            System.out.println("省份排序后: " + students.get(i).getProvince() + " - " + students.get(i).getAge()
                    + "    年龄排序后: " + sortedList.get(i).getProvince() + " - " + sortedList.get(i).getAge());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class SortStudent {
        private String name;
        private int age;
        private String province;
    }
}
