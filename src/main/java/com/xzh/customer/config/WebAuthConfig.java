package com.xzh.customer.config;

import com.xzh.customer.decathlon.authInterceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-11 15:59
 * @description：注意,一个项目中不能有两个类都继承WebMvcConfigurationSupport
 * @modified By：
 * @version:
 */
@Configuration
public class WebAuthConfig extends WebMvcConfigurationSupport {
    @Resource
    AuthInterceptor authInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/auth/**")
        .excludePathPatterns("/auth/test002");
        super.addInterceptors(registry);
    }

    //由于继承WebMvcConfigurationSupport后会导致自动配置失效，所以这里要指定默认的静态资源的位置。
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
