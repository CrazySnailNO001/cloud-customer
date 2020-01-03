package com.xzh.customer.decathlon.currentLimiting.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-02 17:56
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public AccessLimitInterceptor getSessionInterceptor() {
        return new AccessLimitInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSessionInterceptor()).addPathPatterns("/accessLimit/*");
    }
}
