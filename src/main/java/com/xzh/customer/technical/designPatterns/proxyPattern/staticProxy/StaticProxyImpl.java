package com.xzh.customer.technical.designPatterns.proxyPattern.staticProxy;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-19 15:40
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class StaticProxyImpl implements StaticCustomer {
    @Override
    public void buyHouse() {
        System.out.println("我想买个房子.");
    }
}
