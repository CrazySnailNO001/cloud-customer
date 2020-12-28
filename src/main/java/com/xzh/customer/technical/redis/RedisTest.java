package com.xzh.customer.technical.redis;

import com.xzh.customer.technical.object.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author XZHH
 * @Description:
 * leftPushAll: 批量把一个集合插入到列表中
 * leftPushIfPresent:只有存在key对应的列表才能将这个value值插入到key所对应的列表中
 * @create 2019/4/26 0026 17:07
 * @modify By:
 **/
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisTest {
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/set")
    public boolean set() {
        return redisUtils.set("store", "set");
    }

    @GetMapping("/get")
    public String get() {
        return redisUtils.get("store");
    }

    @RequestMapping("/update")
    public boolean update() {
        return redisUtils.update("store", "update");
    }

    @RequestMapping("/delete")
    public boolean delete() {
        return redisUtils.delete("store");
    }

    @RequestMapping("/get_list")
    public List getList() {
        ArrayList resultList;
        String cacheKey = RedisUtils.getRedisKey(
                RedisUtils.REDIS_PREFIX_MODULE_CUSTOMER,
                this.getClass().getSimpleName(),
                "getList",
                "userList");

        if (Objects.requireNonNull(redisTemplate.hasKey(cacheKey))) {
            List<String> range = redisTemplate.opsForList().range(cacheKey, 0, -1);
            if (CollectionUtils.isEmpty(range))
                return null;

            resultList = (ArrayList) range;
            log.info("get locationList from cache... " + resultList);
        } else {
            ArrayList<User> users = new ArrayList<>();
            User user = new User("张三", 12);
            users.add(user);
            resultList = (ArrayList) users.stream().map(User::getName).collect(Collectors.toList());
            redisTemplate.opsForList().leftPushAll(cacheKey, resultList);
            return resultList;
        }

        return resultList;
    }
}
