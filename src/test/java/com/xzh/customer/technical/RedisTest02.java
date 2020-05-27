package com.xzh.customer.technical;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author ：xzh
 * @date ：Created in 2020-05-19 17:43
 * @description：
 * @modified By：
 * @version:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedisTest02 {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisTest02() {
        redisTemplate.opsForValue().set("test01", "aa");
        log.info(redisTemplate.opsForValue().get("test01"));
    }
}
