package com.xzh.customer.technical.designPatterns.proxyPattern.staticProxy;

import com.xzh.customer.technical.designPatterns.proxyPattern.ProxyCustomer;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-19 15:40
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class StaticCustomerProxy implements ProxyCustomer {
    private final ProxyCustomer customer;

    StaticCustomerProxy(ProxyCustomer customer) {
        this.customer = customer;
    }

    @Override
    public void buyHouse() {
        System.out.println("买房前准备");
        customer.buyHouse();
        System.out.println("买房后装修");
    }
}
