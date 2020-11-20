package com.xzh.customer.config;

import com.xzh.customer.technical.spring.filter.MyTestFilter;
import com.xzh.customer.technical.spring.filter.MyTestFilter02;
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
    @Resource
    private MyTestFilter02 myTestFilter02;

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(myTestFilter);
        registration.addUrlPatterns("/filter/*");
        registration.setName("testFilter");
        registration.setOrder(1); //当有多个过滤器的时候,order值小的先执行
        return registration;
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration02() {
        FilterRegistrationBean registration = new FilterRegistrationBean(myTestFilter02);
        registration.addUrlPatterns("/filter/*");
        registration.setName("testFilter02");
        registration.setOrder(2); //当有多个过滤器的时候,order值小的先执行
        return registration;
    }

}
