package com.xzh.customer.decathlon.asyncAndDi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-17 11:01
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/asyncAndDi")
public class AsyncAndDiController {
    @Autowired
    private AsyncAndDiServiceA asyncAndDiServiceA;

    @GetMapping("/test")
    public String test() {
        String test = asyncAndDiServiceA.test();
        return test;
    }
}
