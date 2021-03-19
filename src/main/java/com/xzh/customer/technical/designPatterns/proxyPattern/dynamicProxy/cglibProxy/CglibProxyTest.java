package com.xzh.customer.technical.designPatterns.proxyPattern.dynamicProxy.cglibProxy;

import com.xzh.customer.technical.designPatterns.proxyPattern.ProxyCustomerImpl;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-19 16:10
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class CglibProxyTest {
    public static void main(String[] args) {

        //目标对象
        CglibCustomer customer = new CglibCustomer();
        //代理对象
        CglibProxy cglibProxy = new CglibProxy(customer);
        CglibCustomer instance = (CglibCustomer) cglibProxy.getInstance();
        //执行代理对象的方法
        instance.buyHouse();
    }
}
