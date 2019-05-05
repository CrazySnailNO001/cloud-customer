package com.xzh.customer.cloud.feign;

import com.xzh.customer.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
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
// name的值是服务提供者的配置文件中的spring.application.name
@FeignClient(name= "provider-service",fallback = FeignHystrixServiceImpl.class,configuration = FeignConfig.class)
//@FeignClient(name= "provider-service",fallbackFactory = FeignFallbackFactory.class)
public interface FeignHystrixService {
    @GetMapping (value = "/hello")
    String hello(@RequestParam(value = "name") String name);

    @RequestMapping("/time_out/{time}")
    String testTimeOutFeign(@PathVariable("time") Long time);
}
