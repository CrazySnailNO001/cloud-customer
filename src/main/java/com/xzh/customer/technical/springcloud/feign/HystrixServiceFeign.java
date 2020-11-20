package com.xzh.customer.technical.springcloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/28 0028 14:48
 * @modify By:
 **/
@FeignClient(name = "${feign.client.provide.name}", path = "${feign.client.provide.path}"
//        ,fallback = FeignFallbackImpl.class
//        ,configuration = FeignConfig.class   //只有feign发生异常才进来

        , fallbackFactory = FeignFallbackFactory.class
)
//@Component
public interface HystrixServiceFeign {
    @GetMapping("/time_out/{time}")
    String testTimeOutFeign(@PathVariable("time") Long time);

    /**
     * create by: xzh
     * description: Hello World API
     * create time: 2020-03-30 15:52
     */
    @GetMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);

    /**
     * create by: xzh
     * description: 测试 timeout API
     * create time: 2020-03-30 15:52
     */
    @GetMapping("/hystrix_timeout")
    String hystrixTimeOut(@RequestParam long time);
}
