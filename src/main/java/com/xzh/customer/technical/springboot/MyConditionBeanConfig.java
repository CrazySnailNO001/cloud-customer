package com.xzh.customer.technical.springboot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-24 17:28
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class MyConditionBeanConfig {
    @Bean
    public Demo Demo() {
        return new Demo();
    }

    @Bean
    @ConditionalOnBean(Demo.class)//有Bean就加载
    public ClassAA classAA() {
        return new ClassAA();
    }

    @Bean
    @ConditionalOnMissingBean(Demo.class)//没有Bean就加载
    public ClassBB ClassBB() {
        return new ClassBB();
    }

    @Bean
    @ConditionalOnClass(Demo.class) //有class就加载
    public ClassC classC() {
        return new ClassC();
    }

    @Bean
    @ConditionalOnMissingClass("com.xzh.customer.technical.springboot.MyConditionBeanConfig") //没有class就加载
    public ClassD classD() {
        return new ClassD();
    }

    @Bean
    @ConditionalOnMissingClass("com.xzh.customer.technical.springboot.MyConditionBeanConfig.NoThisBean.class")
    public ClassE classE() {
        return new ClassE();
    }

    private static class Demo {

    }

    public static class ClassAA {

    }

    public static class ClassBB {

    }

    public static class ClassC {

    }

    public static class ClassD {

    }

    public static class ClassE {

    }
}
