package com.xzh.customer.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XZHH
 * @Description: redis操作工具类(基于RedisTemplate).
 * @create 2019/4/26 0026 16:40
 * @modify By:
 **/
@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            Map hashMap = new HashMap();
//            redisTemplate.opsForHash().put("hash",hashMap,"");
            redisTemplate.opsForHash().putAll("ddd", hashMap);
            redisTemplate.opsForSet().add("dd", "dd","dd22");
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新缓存
     */
    public boolean update(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
