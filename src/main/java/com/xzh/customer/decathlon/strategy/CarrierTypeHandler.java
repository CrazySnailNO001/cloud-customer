package com.xzh.customer.decathlon.strategy;

import com.xzh.customer.decathlon.strategy.constants.CarrierEnum;

import java.lang.annotation.*;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-02 10:23
 * @description：
 * @modified By：
 * @version:
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CarrierTypeHandler {
    CarrierEnum value();
}
