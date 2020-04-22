package com.xzh.customer.technical.myData;

import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ：xzh
 * @date ：Created in 2020-04-21 11:51
 * @description：如果需要使用remove(key)方法,必须使用方法三,否则,若你使用的jdk版本在1.8以上建议使用方法master,否则推荐方法一
 * @modified By：
 * @version:
 */
@Log4j
public class MapForeach {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("张三", 12);
        map.put("李四", 13);
        map.put("王五", 14);
        map.put("周六", 15);

        /**
         * java8 lumbda 效率最高
         */
        map.forEach((key, value) ->
                System.out.println("[Method master] Key = " + key + ", Value = " + value)
        );


        /**
         * 方法一 在for-each循环中使用entries来遍历
         * for-each循环在java 5中被引入所以该方法只能应用于java 5或更高的版本中。
         */
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("[Method 001] key : [" + entry.getKey() + "], value : [" + entry.getValue() + "]");
        }
        System.out.println();

        /**
         * 方法二 在for-each循环中遍历keys或values
         * 如果只需要map中的键或者值，你可以通过keySet或values来实现遍历，而不是用entrySet。
         * 该方法比entrySet遍历在性能上稍好（快了10%），而且代码更加干净。
         */
        for (String key : map.keySet()) {
            System.out.println("[Method 002] key : [" + key + "]");
        }
        for (Integer value : map.values()) {
            System.out.println("[Method 002] value : [" + value + "]");
        }
        System.out.println();

        /**
         * 方法三 使用Iterator遍历
         * 该种方式看起来冗余却有其优点所在。首先，在老版本java中这是惟一遍历map的方式。
         * 另一个好处是，你可以在遍历时调用iterator.remove()来删除entries，另两个方法则不能。根据javadoc的说明，如果在for-each遍历中尝试使用此方法，结果是不可预测的。
         * 从性能方面看，该方法类同于for-each遍历（即方法二）的性能。
         */
        Iterator<Map.Entry<String, Integer>> entries = map.entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry<String, Integer> entry = entries.next();
            System.out.println("[Method 003-1] Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        System.out.println();

        //同样的,我们也可以对keySet和values使用相同的方法
        Iterator<String> keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            System.out.println("[Method 003-2] Key = " + key + ", Value = " + map.get(key));
        }
        System.out.println();

        /**
         * 方法四 通过键找值遍历（效率低）
         * 作为方法一的替代，这个代码看上去更加干净；但实际上它相当慢且无效率。
         * 因为从键取值是耗时的操作（与方法一相比，在不同的Map实现中该方法慢了20%~200%）。如果你安装了FindBugs，它会做出检查并警告你关于哪些是低效率的遍历。所以尽量避免使用。
         */
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println("[Method 004] Key = " + key + ", Value = " + value);

        }

    }
}
