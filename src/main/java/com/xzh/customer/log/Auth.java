package com.xzh.customer.log;

import java.lang.annotation.*;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/9 0009 14:26
 * @modify By:
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {
}
