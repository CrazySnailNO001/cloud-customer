package com.xzh.customer.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author XZHH
 * @Description:
 * @create 2019/5/5 0005 17:08
 * @modify By:
 **/
@Configuration
public class FeignConfig {
    //Feign在远程调用失败后会进行重试
    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1),5);
    }
}
