package com.xzh.customer.decathlon.strategy;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-02 10:36
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class CarrierServiceListener<T> implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Map<String, Object> beans = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(CarrierTypeHandler.class);

        CarrierServiceContext<T> carrierServiceContext = contextRefreshedEvent.getApplicationContext().getBean(CarrierServiceContext.class);

        beans.forEach((name, bean) -> {
            CarrierTypeHandler typeHandler = bean.getClass().getAnnotation(CarrierTypeHandler.class);
            carrierServiceContext.putCarrierService(typeHandler.value().getName(), (T) bean);
        });

    }
}