package com.xzh.customer.config;

import com.xzh.customer.technical.spring.authInterceptor.AuthInterceptor;
import com.xzh.customer.technical.juc.currentLimiting.redisLimiter.AccessLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-11 15:59
 * @description： 注意, 一个项目中不能有两个类继承 WebMvcConfigurationSupport 或者 WebMvcConfigurer
 * @modified By：
 * @version:
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Resource
    AuthInterceptor authInterceptor;

    @Resource
    private AccessLimitInterceptor accessLimitInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        /**
         * 实现权限管理
         */
        registry.addInterceptor(authInterceptor).addPathPatterns("/auth/**")
                .excludePathPatterns("/auth/test002");

        /**
         * 实现限流功能
         */
        registry.addInterceptor(accessLimitInterceptor).addPathPatterns("/accessLimit/*");

        super.addInterceptors(registry);
    }

    //由于继承WebMvcConfigurationSupport后会导致自动配置失效，所以这里要指定默认的静态资源的位置。
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/resources/");
        super.addResourceHandlers(registry);
    }
}
