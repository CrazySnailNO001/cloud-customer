package com.xzh.customer.technical.designPatterns.proxyPattern.staticProxy;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-19 15:44
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        StaticCustomer customer = new StaticProxyImpl();
        customer.buyHouse();
        System.out.println("=======");
        StaticCustomerProxy customerProxy = new StaticCustomerProxy(customer);
        customerProxy.buyHouse();
    }
}
