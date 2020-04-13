package com.xzh.customer.technical.decathlon.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
        /**
         * 请求头和请求参数是不能直接修改，也没有提供修改的方法，但是可以在过滤器和拦截器中使用HttpServletRequestWrapper包装类达到修改的目的。
         */
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request) {
            @Override
            public String getHeader(String name) {
                // 先从原本的Request中获取头，如果为空且名字为token，则从参数中查找并返回
                String superHeader = super.getHeader(name);
                if ("AuthToken".equals(name) && StringUtils.isEmpty(superHeader)) {
                    String token = request.getParameter("AuthToken");
                    if (StringUtils.isNotEmpty(token)) {
                        return token;
                    }else{
                        System.out.println("没有token");
                        return "meiyoutoken";
                    }
                }
                return superHeader;
            }

            /**
             * 要想增加一个请求参数可以在HttpServletRequestWrapper中重写getParameter(String name)
             * 注意： getParameterMap()返回的时一个不可修改的map ,不能直接向里面put值， 所以在重写这个方法时要自己new 一个HashMap ，
             * 然后在新建的map中放值，最后返回时必须调用Collections.unmodifiableMap(Map<? extends K, ? extends V> m) 把map改成不可变的
             * @param name
             * @return
             */
            @Override
            public String getParameter(String name) {
                if("newParam".equals(name)){
                    return "这是我新增加的参数";
                }
                return super.getParameter(name);
            }
            @Override
            public Map<String, String[]> getParameterMap() {
                HashMap<String, String[]> newMap = new HashMap<>();
                newMap.putAll(super.getParameterMap());
                newMap.put("newParam",new String[]{"这是我新增加的参数"}) ;
                return Collections.unmodifiableMap(newMap);
            }
            @Override
            public String[] getParameterValues(String name) {
                if("newParam".equals(name)){
                    return new String[]{"这是我新增加的参数"};
                }
                return super.getParameterValues(name);
            }

        };


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
