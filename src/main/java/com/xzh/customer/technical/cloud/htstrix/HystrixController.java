package com.xzh.customer.technical.cloud.htstrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xzh.customer.technical.cloud.feign.HystrixServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-25 11:53
 * @description：配置优先级:动态配置实例属性>内置实例默认值>动态全局默认属性>内置全局默认值
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {
    @Autowired
    private HystrixServiceFeign hystrixServiceFeign;

    @HystrixCommand(commandKey = "hystrixTest001"
//            ,commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "50")}
    )
    @GetMapping(value = "/hello", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public String hystrixTest001(String name) {
        return hystrixServiceFeign.hello(name);
    }

    @HystrixCommand(commandKey = "hystrixTimeoutTest")
    @GetMapping(value = "/hystrixTimeoutTest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public String hystrixTimeoutTest(String name) {
        return hystrixServiceFeign.hello(name);
    }
}
