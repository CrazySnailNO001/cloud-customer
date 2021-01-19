package com.xzh.customer.technical.designPatterns.proxyPattern.dynamicProxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-19 15:46
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class MyDynamicProxyHandler implements InvocationHandler {
    private final Object object;

    MyDynamicProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买房前准备.");
        Object result = method.invoke(object, args);
        System.out.println("买房后装修.");
        return result;
    }
}
