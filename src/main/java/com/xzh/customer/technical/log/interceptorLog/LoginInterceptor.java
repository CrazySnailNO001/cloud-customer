package com.xzh.customer.technical.log.interceptorLog;

import com.xzh.customer.technical.log.Auth;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/9 0009 14:25
 * @modify By:
 **/
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("===========");
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        if (clazz.isAnnotationPresent(Auth.class) ||
                method.isAnnotationPresent(Auth.class)) {

            Object subject = SecurityUtils.getSubject().getPrincipal();
            if (subject == null) {
                response.setStatus(401);
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }
}
