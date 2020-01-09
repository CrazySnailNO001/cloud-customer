package com.xzh.customer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author ：xzh
 * @date ：Created in 2019-10-25 15:15
 * @description：
 * @modified By：
 * @version:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisTemplate<String, Integer> stringIntegerRedisTemplate;

    @Test
    public void redisTest01() throws InterruptedException {
        stringIntegerRedisTemplate.opsForValue().set("testData", 12, 20, TimeUnit.SECONDS);
        log.info("testData value : [ {} ] 过期时间 --001 [ {} ]", stringIntegerRedisTemplate.opsForValue().get("testData"),
                stringIntegerRedisTemplate.getExpire("testData"));

        Thread.sleep(5000);
        stringIntegerRedisTemplate.opsForValue().set("testData", 13);
        log.info("testData value : [ {} ] 过期时间 --002 [ {} ]", stringIntegerRedisTemplate.opsForValue().get("testData"),
                stringIntegerRedisTemplate.getExpire("testData"));
    }

    @Test
    public void redisTest02() throws InterruptedException {
        stringIntegerRedisTemplate.opsForValue().set("testData", 12, 20, TimeUnit.SECONDS);
        log.info("testData value : [ {} ] 过期时间 --001 [ {} ]", stringIntegerRedisTemplate.opsForValue().get("testData"),
                stringIntegerRedisTemplate.getExpire("testData"));

        Thread.sleep(5000);
        stringIntegerRedisTemplate.opsForValue()
                .set("testData", 13, stringIntegerRedisTemplate.getExpire("testData"), TimeUnit.SECONDS);
        log.info("testData value : [ {} ] 过期时间 --002 [ {} ]", stringIntegerRedisTemplate.opsForValue().get("testData"),
                stringIntegerRedisTemplate.getExpire("testData"));
    }

    @Test
    public void redisTest03() throws InterruptedException {
        stringIntegerRedisTemplate.opsForValue().set("testData", 12, 20, TimeUnit.SECONDS);
        log.info("testData value : [ {} ] 过期时间 --001 [ {} ]", stringIntegerRedisTemplate.opsForValue().get("testData"),
                stringIntegerRedisTemplate.getExpire("testData"));

        Thread.sleep(5000);
        stringIntegerRedisTemplate.opsForValue()
                .set("testData", 13, 122L);
        log.info("testData value : [ {} ] 过期时间 --002 [ {} ]", stringIntegerRedisTemplate.opsForValue().get("testData"),
                stringIntegerRedisTemplate.getExpire("testData"));
    }

    @Test
    public void getExpireTimeTest() {
        redisTemplate.opsForValue().set("123", "4345");
        Long expire = redisTemplate.getExpire("123");
        System.out.println(expire);

        redisTemplate.expire("123", 2, TimeUnit.DAYS);
        Long expire1 = redisTemplate.getExpire("123");
        System.out.println(expire1);

//        redisTemplate.persist("123");
        Long expire2 = redisTemplate.getExpire("1231");
        System.out.println(expire2);
    }

    @Test
    public void redisTest002() throws InterruptedException {
        //每次set都会重置过期时间

        redisTemplate.opsForValue().set("aaa", "aaa", 10, TimeUnit.SECONDS);
        System.out.println("aaa time: " + redisTemplate.getExpire("aaa"));

        //使键永久有效
        redisTemplate.persist("aaa");
        System.out.println("aaa time: " + redisTemplate.getExpire("aaa"));

        //每次set都会重置过期时间 重置为 10
        redisTemplate.opsForValue().set("aaa", "aaa", 10, TimeUnit.SECONDS);
        System.out.println("aaa time: " + redisTemplate.getExpire("aaa"));

        Thread.sleep(2000);
        //每次set都会重置过期时间 重置为 -1
        redisTemplate.opsForValue().set("aaa", "aaa");
        System.out.println("aaa time: " + redisTemplate.getExpire("aaa"));

    }

    @Test
    public void getExpireTimeTest2() {
        Long expire = redisTemplate.getExpire("tmalldss:1905041112920");
        System.out.println(expire);
    }

    @Test
    public void test004(){
        redisTemplate.opsForValue().set("time",12,30,TimeUnit.SECONDS);
        System.out.println("过期时间001: " + redisTemplate.getExpire("time"));
        Date date = new Date();
        redisTemplate.expireAt("time",new Date());
        System.out.println("过期时间002: " + redisTemplate.getExpire("time"));
    }
}
