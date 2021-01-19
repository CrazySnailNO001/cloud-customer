package com.xzh.customer.technical.designPatterns.proxyPattern.staticProxy;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-19 15:40
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class StaticCustomerProxy implements StaticCustomer {
    private final StaticCustomer customer;

    public StaticCustomerProxy(StaticCustomer customer) {
        this.customer = customer;
    }

    @Override
    public void buyHouse() {
        System.out.println("买房前准备");
        customer.buyHouse();
        System.out.println("买房后装修");
    }
}
