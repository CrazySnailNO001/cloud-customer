package com.xzh.customer.technical.designPatterns.proxyPattern;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-19 15:40
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class ProxyCustomerImpl implements ProxyCustomer {
    @Override
    public void buyHouse() {
        System.out.println("我想买个房子.");
    }
}
