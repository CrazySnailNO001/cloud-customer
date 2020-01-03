package com.xzh.customer.cloud.feign;

import com.xzh.customer.log.loggingAspect.PerformanceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XZHH
 * @Description:    feign实现服务调用,默认实现 轮训 的负载均衡策略
 * @create 2019/4/28 0028 14:51
 * @modify By:
 **/

@RestController
@RequestMapping("/feign")
@PerformanceLog("feignController")
public class FeignController {
    @Autowired
    private HystrixServiceFeign hystrixServiceFeign;

//    @RequestMapping("/hello/{name}")
//    public String index(@PathVariable("name") String name) {
//        return feignHystrixService.hello(name);
//    }

    @GetMapping("/hello")
    public String aa( String name) {
        return hystrixServiceFeign.hello(name);
    }

    @GetMapping("feign/time_out/{time}")
    public String findUserTimeOutTest(@PathVariable Long time) {
        String user = this.hystrixServiceFeign.testTimeOutFeign(time);
        return user;
    }
}
