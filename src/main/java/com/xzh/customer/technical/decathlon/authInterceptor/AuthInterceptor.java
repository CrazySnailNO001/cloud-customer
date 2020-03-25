package com.xzh.customer.technical.decathlon.authInterceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-11 15:32
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(">>>AuthInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前)");

//        Enumeration<String> e = request.getHeaderNames();
//        while(e.hasMoreElements()){
//            String headerName = e.nextElement();//透明称
//            Enumeration<String> headerValues = request.getHeaders(headerName);
//            while(headerValues.hasMoreElements()){
//                System.out.println(headerName+":"+headerValues.nextElement());
//            }
//        }

        String token = request.getHeader("token");
        log.info("token : [ {} ]", token);


        if (!token.equals("toke20200311"))
            return false;

        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(">>>AuthInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info(">>>AuthInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }
}
