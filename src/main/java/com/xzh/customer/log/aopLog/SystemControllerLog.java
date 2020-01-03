package com.xzh.customer.log.aopLog;

import java.lang.annotation.*;

/**
 * @author XZHH
 * @Description: 自定义controller层日志注解
 * @create 2019/4/9 0009 14:47
 * @modify By:
 **/

@Target({ElementType.PARAMETER, ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
    String description() default "";
}
