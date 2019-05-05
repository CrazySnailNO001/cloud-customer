package com.xzh.customer.cloud.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author XZHH
 * @Description:
 * @create 2019/5/5 0005 15:48
 * @modify By:
 **/
@Component
public class FeignFallbackFactory implements FallbackFactory<FeignHystrixService>{
    @Override
    public FeignHystrixService create(Throwable throwable) {
        return new FeignHystrixService() {
            @Override
            public String hello(String name) {
                return "hello请求失败,进入fallback";
            }

            @Override
            public String testTimeOutFeign(Long time) {
                return "testTimeOutFeign请求失败,进入fallback";
            }
        };
    }
}
