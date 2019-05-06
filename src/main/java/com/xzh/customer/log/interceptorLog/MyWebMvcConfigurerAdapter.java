package com.xzh.customer.log.interceptorLog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.Properties;

/**
 * @author XZHH
 * @Description: 基于拦截器实现日志管理
 * @create 2019/4/9 0009 14:24
 * @modify By:
 **/
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Autowired
    LogInterceptor logInterceptor;
    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("tools.properties"));
            registry.addResourceHandler("/resources/**").addResourceLocations("file:///" + properties.getProperty("upload") + "/resources/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/resources/*");
    }
}
