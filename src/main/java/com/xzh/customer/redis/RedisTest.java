package com.xzh.customer.redis;

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
    public void set() {
        redisUtils.set("diyige", "yes");
    }

    @GetMapping("/get")
    public String get() {
        return redisUtils.get("diyige");
    }

    @RequestMapping("/update")
    public void update() {
        redisUtils.update("diyige", "no");
    }

    @RequestMapping("/delete")
    public void delete() {
        redisUtils.delete("diyige");
    }
}
