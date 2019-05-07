package com.xzh.customer.cloud.ribbon;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author XZHH
 * @Description:
 * @create 2019/5/5 0005 14:45
 * @modify By:
 **/
@Service
public class RibbonHystrixService {
    @Autowired
    private RestTemplate rest;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @HystrixCommand(fallbackMethod = "fallback")
    public String helloPost(String name) {
        String url = "http://provider-service/hello";
        User user = new User();
        user.setName(name);
        user.setId(1);
        String s1 = JSON.toJSONString(user);
        return rest.postForObject(url, s1, String.class);
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public String helloGet(String name) {
        String url = "http://provider-service/hello?name=";
        return rest.getForObject(url + name, String.class);
    }

    public String fallback(String name){
        logger.info("发生异常,进入fallback方法,接收参数: name =  {}",name);
        return "fallback";
    }
}
