package com.xzh.customer.technical.springboot.conditional;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
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
    @ConditionalOnMissingClass("com.xzh.customer.technical.springboot.conditional.MyConditionBeanConfig") //没有class就加载
    public ClassD classD() {
        return new ClassD();
    }

    @Bean
    @ConditionalOnMissingClass("com.xzh.customer.technical.springboot.MyConditionBeanConfig.NoThisBean.class")
    public ClassE classE() {
        return new ClassE();
    }

    @Bean
    @ConditionalOnProperty(prefix = "biz.data.autoConfig.property",name = "classF",havingValue = "hello")
    public ClassF classF(){
        return new ClassF();
    }

    @Bean
    @Conditional(MyCondition.class)
    public ClassG classG(){
        return new ClassG();
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

    public static class ClassF {

    }

    public static class ClassG {

    }
}
