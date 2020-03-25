package com.xzh.customer.technical.decathlon.currentLimiting.redisLimiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-02 17:46
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/accessLimit")
@Slf4j
public class AccessLimitController {

    @GetMapping("/test001")
    @AccessLimit(limit = 4,sec = 10)
    @ResponseBody
    public String test001(HttpServletRequest request, @RequestParam String name){

        return name + " hello world!!!";
    }
}
