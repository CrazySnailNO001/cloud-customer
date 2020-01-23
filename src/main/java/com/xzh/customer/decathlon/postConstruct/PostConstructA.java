package com.xzh.customer.decathlon.postConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
public class PostConstructA {
    @Autowired
    private PostConstructB postConstructB;

    public PostConstructA() {
        System.out.println("这是PostConstructA 的构造方法");
    }

    @PostConstruct
    private void init() {
        System.out.println("这是PostConstructA的 init 方法");
        postConstructB.test();
    }
}
