package com.xzh.customer.decathlon.strategy;

public interface ICarrierService<T> {

    String createOrder();

    //这两个是增强方法
    default String deliveryQuery() {
        return " don`t have deliveryQuery method";
    }
}
