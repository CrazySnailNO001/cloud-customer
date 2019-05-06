package com.xzh.customer.cloud.feign;

import org.springframework.stereotype.Component;

/**
 * @author XZHH
 * @Description:
 * @create 2019/5/5 0005 15:20
 * @modify By:
 **/
@Component
public class FeignHystrixServiceImpl implements FeignHystrixService {

    @Override
    public String testTimeOutFeign(Long time) {
        System.out.println("testTimeOutFeign请求失败,进入fallback");
        return "testTimeOutFeign请求失败,进入fallback";
    }

    /**
     * hystrix fallback方法
     * @param name name
     * @return 默认的用户
     */
    @Override
    public String hello(String name) {
        System.out.println("hello请求失败,进入fallback");
        return "hello请求失败,进入fallback";
    }
}