//package com.xzh.customer.config;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author ：xzh
// * @date ：Created in 2020-04-29 17:57
// * @description：对所有使用feign调用的请求进行特殊处理
// * @modified By：
// * @version:
// */
//@Configuration
//@Log4j2
//public class FeignRequestConfig implements RequestInterceptor {
//
//    private static final String HEADER_REFERER = "referer";
//
//    /**
//     * create by: xzh
//     * description: 所有的feign请求,header头中都加上referer
//     * create time: 2020-04-29 18:00
//     */
//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        log.info("Customer Module feign request config add header {}", HEADER_REFERER);
//        requestTemplate.header(HEADER_REFERER, "referer");
//    }
//}
