package com.xzh.customer.technical.cloud.hystrix;

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
 * @modified By： 默认情况下,hystrix按照组别建分配线程池,线程池键可以做到,请求依旧在组别键呢统计,但在同一个组别键下,安排不通的线程运行Hystrix命令
 * HystrixCommand注解属性:
 * commandKey:默认注解的方法名称
 * groupKey:默认注解方法的类名称
 * threadPoolKey:默认定义为groupKey
 * commandProperties:配置Hystrix配置参数
 * threadPoolProperties:配置舱壁隔离线程池参数
 * ignoreExceptions:定义需要忽略的异常,未被忽略的异常都会被包装成HystrixRuntimeException
 * raiseHystrixExceptions:定义需要抛出的异常,未被抛出的异常都会被包装成HystrixRuntimeException
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

    @HystrixCommand(commandKey = "hystrixTimeoutTest",groupKey = "",threadPoolKey = "")
    @GetMapping(value = "/hystrixTimeoutTest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public String hystrixTimeoutTest(String name) {
        return hystrixServiceFeign.timeout(name);
    }

    @HystrixCommand(commandKey = "HystrixConfigTestKey")
    @GetMapping(value = "/hystrixConfigTestKey", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public String hystrixConfigTestKey(String name) {
        return hystrixServiceFeign.timeout(name);
    }
}
