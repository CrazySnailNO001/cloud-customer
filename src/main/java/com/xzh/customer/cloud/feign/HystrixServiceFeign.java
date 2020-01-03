package com.xzh.customer.cloud.feign;

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
@FeignClient(name= "provider-service",fallback = FeignFallbackImpl.class,configuration = FeignConfig.class)
//@FeignClient(name= "provider-service",fallbackFactory = FeignFallbackFactory.class)
//@FeignClient(name= "provider-service")
@Component
public interface HystrixServiceFeign {
    @GetMapping (value = "/hello")
    String hello(@RequestParam(value = "name") String name);

    @RequestMapping("/time_out/{time}")
    String testTimeOutFeign(@PathVariable("time") Long time);
}
