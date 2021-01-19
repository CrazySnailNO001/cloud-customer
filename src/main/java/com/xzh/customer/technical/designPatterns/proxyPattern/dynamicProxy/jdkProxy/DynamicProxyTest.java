package com.xzh.customer.technical.designPatterns.proxyPattern.dynamicProxy.jdkProxy;

import com.xzh.customer.technical.designPatterns.proxyPattern.ProxyCustomer;
import com.xzh.customer.technical.designPatterns.proxyPattern.ProxyCustomerImpl;

import java.lang.reflect.Proxy;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-19 15:49
 * @description：
 * 注意Proxy.newProxyInstance()方法接受三个参数：
 * ClassLoader loader:指定当前目标对象使用的类加载器,获取加载器的方法是固定的
 * Class<?>[] interfaces:指定目标对象实现的接口的类型,使用泛型方式确认类型
 * InvocationHandler:指定动态处理器，执行目标对象的方法时,会触发事件处理器的方法
 * @modified By：xzh
 * @version: V1.0.0
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        ProxyCustomer customer = new ProxyCustomerImpl();
        ProxyCustomer proxyBuyHouse = (ProxyCustomer) Proxy.newProxyInstance(ProxyCustomer.class.getClassLoader(), new
                Class[]{ProxyCustomer.class}, new MyDynamicProxyHandler(customer));
        proxyBuyHouse.buyHouse();
    }
}
