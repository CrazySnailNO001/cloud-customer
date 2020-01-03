package com.xzh.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableEurekaClient
//@EnableCircuitBreaker
@SpringCloudApplication  //相当于上面3个的注解
@EnableFeignClients(basePackages = {"com.xzh.customer.cloud.feign"})
@EnableTransactionManagement //开启事务
//@ComponentScan("com.xzh.customer")
@EnableAsync    //开启异步线程
@EnableAspectJAutoProxy(exposeProxy = true)       //开启动态代理
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}


    // 实现负载均衡,默认轮训
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
