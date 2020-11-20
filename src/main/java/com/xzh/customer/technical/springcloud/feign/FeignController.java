package com.xzh.customer.technical.springcloud.feign;

import com.xzh.customer.technical.log.loggingAspect.PerformanceLog;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author XZHH
 * @Description: feign实现服务调用, 默认实现 轮训 的负载均衡策略
 * @create 2019/4/28 0028 14:51
 * @modify By:
 **/

@RestController
@RequestMapping("/feign")
@PerformanceLog("feignController")
public class FeignController {
    @Resource
    private HystrixServiceFeign hystrixServiceFeign;

//    @RequestMapping("/hello/{name}")
//    public String index(@PathVariable("name") String name) {
//        return feignHystrixService.hello(name);
//    }

    @GetMapping(value = "/hello", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE)
    public String aa(@RequestParam String name) {
        return hystrixServiceFeign.hello(name);
    }

    @GetMapping("feign/time_out/{time}")
    public String findUserTimeOutTest(@PathVariable Long time) {
        return this.hystrixServiceFeign.testTimeOutFeign(time);
    }
}
