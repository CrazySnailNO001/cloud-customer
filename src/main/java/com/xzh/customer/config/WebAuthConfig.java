package com.xzh.customer.config;

import com.xzh.customer.decathlon.auth.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-11 15:59
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class WebAuthConfig extends WebMvcConfigurationSupport {
    @Resource
    AuthInterceptor authInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/auth/**");
//        super.addInterceptors(registry);
    }
}
