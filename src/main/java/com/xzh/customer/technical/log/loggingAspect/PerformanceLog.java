package com.xzh.customer.technical.log.loggingAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Zhonghui XU
 * @Date: 2019-03-23
 */

/**
 * Wrap annotated methods, or methods of annotated classes, with a log containing the classe name and method name
 * as well as the execution time.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PerformanceLog {
    String value() default "";
}
