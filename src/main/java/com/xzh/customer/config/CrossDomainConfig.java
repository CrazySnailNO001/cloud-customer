package com.xzh.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author ：xzh
 * @date ：Created in 2020-04-13 20:37
 * @description：
 * @modified By：解决跨域问题
 * @version:
 */
@Configuration
public class CrossDomainConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // allow any damian name
        corsConfiguration.addAllowedHeader("*"); // allow any custom header
        corsConfiguration.addAllowedMethod("*"); // allow any request method（post、get, put, delete,option）
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // enable all interface support cross domain
        return new CorsFilter(source);
    }
}
