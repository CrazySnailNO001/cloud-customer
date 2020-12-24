package com.xzh.customer.technical.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：xzh
 * @date ：Created in 2020-12-24 16:49
 * @description： 自定义分布式锁注解,加了次注解的方法执行时会自动使用分布式锁
 * @modified By：xzh
 * @version: V1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    String value() default "";

    int time() default 30;
}
