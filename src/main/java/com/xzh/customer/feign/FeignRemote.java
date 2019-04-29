package com.xzh.customer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/28 0028 14:48
 * @modify By:
 **/
// name的值是服务提供者的配置文件中的spring.application.name
@FeignClient(name= "provider-service")
public interface FeignRemote {
    @GetMapping (value = "/hello")
    String hello(@RequestParam(value = "name") String name);
}
