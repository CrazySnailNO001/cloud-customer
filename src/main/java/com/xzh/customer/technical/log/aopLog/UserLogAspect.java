package com.xzh.customer.technical.log.aopLog;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/9 0009 17:10
 * @modify By:
 **/
@Aspect
@Component
@Order(-5)
public class UserLogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.xzh.personalproject.congig.aopLog.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {

        // 接收到请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString() +
                ",HTTP_METHOD : " + request.getMethod() +
                ",IP : " + request.getRemoteAddr() +
                ", CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() +
                "." + joinPoint.getSignature().getName() +
                ", ARGS : " + Arrays.toString(joinPoint.getArgs()) +
                ", Headers-Uuid : " + request.getHeader("Uuid"));
        //获取所有参数方法一：
        Enumeration<String> enu = request.getParameterNames();
        StringBuilder builder = new StringBuilder();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            builder.append(paraName + ": " + request.getParameter(paraName) + ", ");
        }
        logger.info(builder.toString());
    }

    @AfterReturning(value = "webLog()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        // 处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");
        String returnValue = getValue(result);
        logger.info("Method Return value : " + returnValue);

    }

    @AfterThrowing(value = "webLog()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
        logger.error("Cause : " + exception.getMessage());
        logger.error("Cause : " + exception.getCause());
    }

    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }
}
