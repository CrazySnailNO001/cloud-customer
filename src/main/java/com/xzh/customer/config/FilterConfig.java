package com.xzh.customer.config;

import com.xzh.customer.decathlon.filter.MyTestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-15 20:37
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new MyTestFilter());
        registration.addUrlPatterns("/filter/*");
        registration.setName("testFilter");
        return registration;
    }
}
