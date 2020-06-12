package com.xzh.customer;

import com.xzh.customer.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.Instant;
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

    /**
     * 有key存在的时候才能存进去
     */
    @Test
    public void setIfPresent() {
        Boolean result = redisTemplate.opsForValue().setIfPresent("setIfPresent", "data", 6000, TimeUnit.MILLISECONDS);
        System.out.println(result);
    }

    /**
     * 不存在key的时候才能存进去
     */
    @Test
    public void setIfAbsent() {
        Boolean result = redisTemplate.opsForValue().setIfAbsent("setIfAbsent", "data", 6000, TimeUnit.MILLISECONDS);
        System.out.println(result);
    }

    @Test
    public void redisTest01() throws InterruptedException {
        stringIntegerRedisTemplate.opsForValue().set("testData", 12, 20, TimeUnit.SECONDS);
        log.info("testData value : [ {} ] 过期时间 --001 [ {} ]", stringIntegerRedisTemplate.opsForValue().get("testData"),
                stringIntegerRedisTemplate.getExpire("testData"));

        Thread.sleep(5000);
//        stringIntegerRedisTemplate.opsForValue().getAndSet("testData", 13);
        stringIntegerRedisTemplate.opsForValue().set("testData", 13); //都会重置过期时间
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
    public void test004() throws InterruptedException {
        redisTemplate.opsForValue().set("time", 12, 30, TimeUnit.SECONDS);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_SECOND);


        Long time = redisTemplate.getExpire("time");
        System.out.println(simpleDateFormat.format(new Date(Instant.now().toEpochMilli())) + ",过期时间001: " + time);

        Date date = new Date(Instant.now().toEpochMilli() + time * 1000);
        System.out.println("到 " + simpleDateFormat.format(date) + "过期");
        Thread.sleep(5000);

        redisTemplate.expireAt("time", date);
        System.out.println(simpleDateFormat.format(new Date(Instant.now().toEpochMilli())) + ",过期时间002: " + redisTemplate.getExpire("time"));
    }

    @Test
    public void testDate001() throws InterruptedException {
        Instant now = Instant.now();
        Date date = new Date(now.toEpochMilli());
        System.out.println(date.getTime());


        Thread.sleep(5000);

        Instant now1 = Instant.now();
        Date date1 = new Date(now1.toEpochMilli());
        System.out.println(date1.getTime());
    }

    @Test
    public void testDate002() {
        Instant now = Instant.now();
        Date date = new Date(now.toEpochMilli());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_SECOND);
        System.out.println(simpleDateFormat.format(date));
    }
}
