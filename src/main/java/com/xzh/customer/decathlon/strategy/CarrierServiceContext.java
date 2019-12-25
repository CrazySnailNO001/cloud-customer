package com.xzh.customer.decathlon.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-02 10:34
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class CarrierServiceContext<T> {
    private final Map<String, T> handlerMap = new HashMap<>();

    public T getCarrierSevice(String name) {
        return handlerMap.get(name);
    }

    public void putCarrierService(String name, T businessService) {
        handlerMap.put(name, businessService);
    }
}
