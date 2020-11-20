package com.xzh.customer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringCloudApplication
@EnableHystrixDashboard
@EnableFeignClients(basePackages = {"com.xzh.customer.technical.springcloud"})
@EnableTransactionManagement //开启事务
@ComponentScan("com.xzh.customer") //包扫描
@EnableAsync    //开启异步线程
@EnableAspectJAutoProxy(exposeProxy = true)       //开启动态代理
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@MapperScan("com.xzh.customer.technical.mybatis.dao")
@EnableJpaAuditing //建表自动加入 创建时间 修改时间 创建者 修改者等(需定义)
//@EnableJpaRepositories(basePackages = "com.xzh.customer.technical.jpa.mongo.student")
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }


    // 实现负载均衡,默认轮训
//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate(){
//        return new RestTemplate();
//    }
}
