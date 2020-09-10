package com.xzh.customer.technical.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：xzh
 * @date ：Created in 2020-09-01 16:50
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class BeanConfig {
    @Bean(name = "testBean", initMethod = "init", destroyMethod = "destroy")
    public MyBean myBean() {
        return new MyBean();
    }
}
