package com.xzh.customer.technical.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/26 0026 17:07
 * @modify By:
 **/
@RestController
@RequestMapping("/redis")
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/set")
    public boolean set() {
        return redisUtils.set("diyige", "set");
    }

    @GetMapping("/get")
    public String get() {
        return redisUtils.get("diyige");
    }

    @RequestMapping("/update")
    public boolean update() {
        return redisUtils.update("diyige", "update");
    }

    @RequestMapping("/delete")
    public boolean delete() {
        return redisUtils.delete("diyige");
    }
}
