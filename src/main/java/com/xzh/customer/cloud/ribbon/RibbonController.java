package com.xzh.customer.cloud.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XZHH
 * @Description: ribbon实现服务调用
 * @create 2019/4/28 0028 15:05
 * @modify By:
 **/
@RestController()
@RequestMapping("/ribbon")
public class RibbonController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RibbonHystrixService ribbonHystrixService;

    @PostMapping("/hello")
    public String helloPost(String name) {
        return ribbonHystrixService.helloPost(name);
    }

    @GetMapping("/hello")
    public String helloGet(String name) {
        return ribbonHystrixService.helloGet(name);
    }

    @GetMapping("/getServiceInfo")
    public String getServiceInfo() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("provider-service");
        return "正在调用服务提供者信息" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"
                + serviceInstance.getPort();
    }
}