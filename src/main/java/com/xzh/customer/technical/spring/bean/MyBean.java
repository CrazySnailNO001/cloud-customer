package com.xzh.customer.technical.spring.bean;

/**
 * @author ：xzh
 * @date ：Created in 2020-09-01 16:51
 * @description：
 * @modified By：
 * @version:
 */
public class MyBean {
    public MyBean(){
        System.out.println("MyBean Initializing");
    }

    public void init(){
        System.out.println("Bean 初始化方法被调用");
    }

    public void destroy(){
        System.out.println("Bean 销毁方法被调用");
    }
}
