package com.xzh.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void getExpireTimeTest(){
        redisTemplate.opsForValue().set("123","4345");
        Long expire = redisTemplate.getExpire("123");
        System.out.println(expire);
        redisTemplate.expire("123",2, TimeUnit.DAYS);
        Long expire1 = redisTemplate.getExpire("123");
        System.out.println(expire1);
        redisTemplate.persist("123");
        Long expire2 = redisTemplate.getExpire("123");
        System.out.println(expire2);
    }

    @Test
    public void getExpireTimeTest2(){
        Long expire = redisTemplate.getExpire("tmalldss:1905041112920");
        System.out.println(expire);
    }
}
