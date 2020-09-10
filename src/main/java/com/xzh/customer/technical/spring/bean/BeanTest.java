package com.xzh.customer.technical.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ：xzh
 * @date ：Created in 2020-09-01 16:51
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class BeanTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        System.out.println(context.getBean("testBean"));
        context.close();
    }
}
