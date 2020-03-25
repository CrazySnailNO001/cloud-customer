package com.xzh.customer.config;


import com.xzh.customer.technical.log.interceptorLog.LogInterceptor;
import com.xzh.customer.technical.log.interceptorLog.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author XZHH
 * @Description: 基于拦截器实现日志管理
 * @create 2019/4/9 0009 14:24
 * @modify By:
 **/
@Configuration
public class WebLogConfig implements WebMvcConfigurer {
    @Autowired
    LogInterceptor logInterceptor;
    @Autowired
    LoginInterceptor loginInterceptor;


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        Properties properties = new Properties();
//        try {
//            properties.load(getClass().getClassLoader().getResourceAsStream("tools.properties"));
//            registry.addResourceHandler("/resources/**").addResourceLocations("file:///" + properties.getProperty("upload") + "/resources/");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        super.addResourceHandlers(registry);
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(logInterceptor);
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/resources/*");
//
////        super.addInterceptors(registry);
//    }
}
