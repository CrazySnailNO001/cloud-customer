package com.xzh.customer.decathlon.postConstruct;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-23 23:04
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class PostConstructB {
    public PostConstructB() {
        System.out.println("这是PostConstructB的 构造方法");
    }

    @PostConstruct
    private void init() {
        System.out.println("这是PostConstructB的 init 方法");
    }

    void test() {
        System.out.println("这是PostConstructB的 test 方法");
    }
}
