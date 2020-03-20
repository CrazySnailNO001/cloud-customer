package com.xzh.customer.config;

import com.xzh.customer.decathlon.filter.MyTestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-15 20:37
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class FilterConfig {

    @Resource
    private MyTestFilter myTestFilter;

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(myTestFilter);
        registration.addUrlPatterns("/filter/*");
        registration.setName("testFilter");
        registration.setOrder(1);
        return registration;
    }
}
