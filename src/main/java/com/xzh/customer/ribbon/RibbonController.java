package com.xzh.customer.ribbon;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author XZHH
 * @Description:    ribbon实现服务调用
 * @create 2019/4/28 0028 15:05
 * @modify By:
 **/
@RestController()
@RequestMapping("/ribbon")
public class RibbonController {
    @Autowired
    private RestTemplate rest;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @PostMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        String url = "http://provider-service/hello";
        User user = new User();
        user.setName(name);
        user.setId(1);
        String s1 = JSON.toJSONString(user);
        return rest.postForObject(url, s1, String.class);
    }

    @GetMapping("/hello")
    public String helloGet(String name) {
        String url = "http://provider-service/hello?name=";
        return rest.getForObject(url + name, String.class);

    }

    @GetMapping("/getServiceInfo")
    public String getServiceInfo() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("provider-service");
        return "正在调用服务提供者信息" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"
                + serviceInstance.getPort();
    }
}