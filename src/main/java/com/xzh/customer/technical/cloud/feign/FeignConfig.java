package com.xzh.customer.technical.cloud.feign;

import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
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
    public Retryer feignRetryer() {
        //只有在feign调用出现问题是才会触发,如 feign.client.config.provider-service.connectTimeout 导致的超时,会触发,
        //而 hystrix.command超时则不会触发

        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 5);
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("username", "password");
    }
}
