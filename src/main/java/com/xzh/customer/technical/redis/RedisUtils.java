package com.xzh.customer.technical.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

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

    public static final Integer DEFAULT_TIMEOUT_MINUTES = 60;
    public static final String REDIS_PREFIX_MODULE_CUSTOMER = "customer";
    private static final String SPLIT = ":";

    public static String getRedisKey(String... name) {
        StringBuffer sb = new StringBuffer();
        for (String str : name) {
            if (StringUtils.isNotBlank(str)) {
                sb.append(str);
                sb.append(SPLIT);
            }
        }
        sb = sb.deleteCharAt(sb.lastIndexOf(SPLIT));

        return sb.toString();
    }


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
