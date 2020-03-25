package com.xzh.customer.technical.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/29 0029 17:12
 * @modify By:
 **/
@RestController
@RequestMapping("/rocketMq")
public class TestController {
    @Autowired
    private RocketMQProvider rocketMQProvider;
    @Autowired
    private RocketMQConsumer rocketMQConsumer;

    @RequestMapping("/pro")
    public String proMq() {
        rocketMQProvider.defaultMQProducer();
        return "OK";
    }

    @RequestMapping("/con")
    public String conMq() {
        rocketMQConsumer.defaultMQPushConsumer();
        return "OK";
    }
}
