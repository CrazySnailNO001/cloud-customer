package com.xzh.customer.technical.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/28 0028 14:48
 * @modify By:
 **/
@FeignClient(name= "${feign.client.provide.name}",path = "${feign.client.provide.path}",fallback = FeignFallbackImpl.class,configuration = FeignConfig.class)
//@FeignClient(name= "provider-service",fallbackFactory = FeignFallbackFactory.class)
//@FeignClient(name= "provider-service")
@Component
public interface HystrixServiceFeign {
    /**
     * create by: xzh
     * description: Hello World API
     * create time: 2020-03-30 15:52
     */
    @GetMapping (value = "/hello")
    String hello(@RequestParam(value = "name") String name);

    @RequestMapping("/time_out/{time}")
    String testTimeOutFeign(@PathVariable("time") Long time);

    /**
     * create by: xzh
     * description: 测试 timeout API
     * create time: 2020-03-30 15:52
     */
    @GetMapping (value = "/timeout")
    String timeout(@RequestParam(value = "name") String name);
}
