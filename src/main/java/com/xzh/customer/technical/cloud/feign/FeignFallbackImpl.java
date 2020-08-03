package com.xzh.customer.technical.cloud.feign;

import org.springframework.stereotype.Component;

/**
 * @author XZHH
 * @Description:
 * @create 2019/5/5 0005 15:20
 * @modify By:
 **/
@Component
public class FeignFallbackImpl extends GeneralFallbackResponse implements HystrixServiceFeign {

    @Override
    public String testTimeOutFeign(Long time) {
        System.out.println("testTimeOutFeign请求失败,进入FeignFallbackImpl.fallback");
        return "testTimeOutFeign请求失败,进入fallback";
    }

    /**
     * hystrix fallback方法
     *
     * @param name name
     * @return 默认的用户
     */
    @Override
    public String hello(String name) {
        return super.generalFail();
    }

    @Override
    public String hystrixTimeOut(long time) {
        return super.generalFail();
    }
}