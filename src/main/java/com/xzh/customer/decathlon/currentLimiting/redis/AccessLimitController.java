package com.xzh.customer.decathlon.currentLimiting.redis;

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
public class AccessLimitController {

    @GetMapping("/test001")
    @AccessLimit(limit = 4,sec = 10)
    @ResponseBody
    public String test001(HttpServletRequest request, @RequestParam String name){

        return name + " hello world!!!";
    }
}
