package com.xzh.customer.technical.juc.currentLimiting.rateLimiter;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitAnnotation {
    double value() default 1;
}
