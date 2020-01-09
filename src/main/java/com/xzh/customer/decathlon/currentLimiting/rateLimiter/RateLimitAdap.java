package com.xzh.customer.decathlon.currentLimiting.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import com.xzh.customer.decathlon.currentLimiting.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-05 15:38
 * @description：
 * @modified By：
 * @version:
 */

@Component
@Scope
@Aspect
@Slf4j
public class RateLimitAdap {

    @Pointcut("@annotation(com.xzh.customer.decathlon.currentLimiting.rateLimiter.RateLimitAnnotation)")
    public void serviceLimit() {

    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletResponse response = sra.getResponse();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(RateLimitAnnotation.class)) {
            RateLimitAnnotation annotation = method.getAnnotation(RateLimitAnnotation.class);
            double limit = annotation.value();

            RateLimiter rateLimiter = RateLimiter.create(limit);
            Boolean flag = rateLimiter.tryAcquire();
            Object obj = null;
            try {
                if (flag) {
                    obj = joinPoint.proceed();
                } else {
                    ResponseUtil.addResponse(response, "failure");
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            System.out.println("flag=" + flag + ",obj=" + obj);
            return obj;
        }
        return joinPoint.proceed();
    }
}
