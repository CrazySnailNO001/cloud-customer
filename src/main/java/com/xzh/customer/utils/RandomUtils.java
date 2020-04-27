package com.xzh.customer.utils;

import lombok.Synchronized;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2020-04-15 10:27
 * @description： 设计模式:双重检查式单例模式
 * @modified By：
 * @version:
 */
public class RandomUtils {
    private static volatile RandomUtils randomUtils;

    private RandomUtils() {
    }

    public static RandomUtils getInstance() {
        if (null == randomUtils)
            synchronized (Synchronized.class) {
                if (null == randomUtils)
                    return new RandomUtils();
            }
        return randomUtils;
    }

    private static Random random = new Random();

    /**
     * 随机生成min和max之间一个数，包括min不包括max
     *
     * @param min
     * @param max
     * @return [min, max)
     */
    public int generateValue(int min, int max) {
        if (min >= max)
            throw new IllegalArgumentException("max 必须大于 min");
        else
            return (int) (random.nextDouble() * (max - min) + min);
    }

    /**
     * description: min - max,不包括max
     * min 必须 < max
     * random.nextFloat 方法返回一个均匀分布在 0 - 1 之间的随机浮点数（包含 0.0f，但不包含 1.0f），乘以最大值和最小值的差，
     * 再强转为 int 类型就可以保证这个随机数在 0 到（最大值-最小值）之间，最后再加上最小值，就恰好可以得到指定范围内的数字。
     * <p>
     * ThreadLocalRandom 类继承自 Random 类，它使用了内部生成的种子来初始化（外部无法设置，所以不能再现测试场景），
     * 并且不需要显式地使用 new 关键字来创建对象（Random 可以通过构造方法设置种子），可以直接通过静态方法 current() 获取针对本地线程级别的对象.
     * 这样做的好处就是，在多线程或者线程池的环境下，可以节省不必要的内存开销。
     */
    private static int getRandomInt(int min, int max) {
        if (min >= max)
            throw new IllegalArgumentException("max 必须大于 min");
        else
//            return (int) (Math.random() * (max - min) + min);
//            return (int) (random.nextFloat() * (max - min) + min);
//            return (int) (random.nextDouble() * (max - min) + min);
//            return random.nextInt(max - min) + min;
//            return new RandomDataGenerator().nextInt(min, max - 1);
            return ThreadLocalRandom.current().nextInt(min, max);
    }


    public static void main(String[] args) {
        int min = 2;
        int max = 11;
        Runnable runnable = () -> {
            int result;
            result = getRandomInt(min, max);
            System.out.println(result);
        };

        IntStream.range(1, 20).forEach(value -> {
            new Thread(runnable).start();
        });


    }

}
