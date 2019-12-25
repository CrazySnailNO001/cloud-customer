package com.xzh.customer.decathlon.currentLimiting;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    int limit() default 5;

    int time() default 5;
}
