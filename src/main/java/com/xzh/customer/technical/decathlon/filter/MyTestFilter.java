package com.xzh.customer.technical.decathlon.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-15 20:06
 * @description：
 * @modified By：
 * @version:
 */
//@Order(2)
//@WebFilter(urlPatterns = "/filter/*", filterName = "myTestFilter")
@Component //与@WebFilter不能同时使用,否则urlPatterns 就不起作用了
@Slf4j
public class MyTestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("[ {} ] 创建啦...", this.getClass().getSimpleName());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("[ {} ] 执行啦...", this.getClass().getSimpleName());

        //不加这句,servlet不会执行请求
        log.info("[ {} ] chain.doFilter开始", this.getClass().getSimpleName());
        chain.doFilter(request, response);
        log.info("[ {} ] chain.doFilter完成", this.getClass().getSimpleName());
    }

    @Override
    public void destroy() {
        log.info("[ {} ] 被摧毁啦...", this.getClass().getSimpleName());
    }
}