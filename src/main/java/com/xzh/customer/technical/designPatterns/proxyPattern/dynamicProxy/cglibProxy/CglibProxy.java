package com.xzh.customer.technical.designPatterns.proxyPattern.dynamicProxy.cglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-19 16:06
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class CglibProxy implements MethodInterceptor {
    //维护目标对象
    private Object target;

    CglibProxy(Object target) {
        this.target = target;
    }

    public Object getInstance() {
        //工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(target.getClass());
        //3.设置回调函数
        enhancer.setCallback(this);
        //4.创建子类(代理对象)
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("买房前准备");
        //执行目标对象的方法
        Object result = method.invoke(target, objects);
        System.out.println("买房后装修");
        return result;
    }
}
