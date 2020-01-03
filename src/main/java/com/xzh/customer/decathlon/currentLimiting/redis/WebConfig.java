package com.xzh.customer.decathlon.currentLimiting.redis;

import com.xzh.customer.decathlon.currentLimiting.redis.AccessLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-02 17:56
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessLimitInterceptor()).addPathPatterns("/**");
    }
}
