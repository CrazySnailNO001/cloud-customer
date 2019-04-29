package com.xzh.customer.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/29 0029 16:16
 * @modify By:
 **/
@Service
public class RocketMQProvider {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    //@PostContruct是spring框架的注解，在方法上加该注解会在项目启动的时候执行该方法，也可以理解为在spring容器初始化的时候执行该方法。
//     @PostConstruct
    public void defaultMQProducer() {
        //生产者的组名
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);

        //指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(namesrvAddr);
        try {
            //Producer对象在使用之前必须要调用start初始化，初始化一次即可
            //注意：切记不可以在每次发送消息时，都调用start方法
            producer.start();

            //创建一个消息实例，包含 topic、tag 和 消息体
            //如下：topic 为 "TopicTest"，tag 为 "push"
            Message message = new Message("TopicTest", "push", "发送消息----xzh-----".getBytes());

            StopWatch stop = new StopWatch();
            stop.start();

            for (int i = 0; i < 10; i++) {
                SendResult result = producer.send(message, new MessageQueueSelector() {

                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, 1);
                //一般消息是通过轮询所有队列来发送的（负载均衡策略），顺序消息可以根据业务，比如说订单号相同的消息发送到同一个队列，这边在代码中指定了1，1处这个值相同的获取到的队列是同一个队列。
                logger.info("发送响应：MsgId:" + result.getMsgId() + "，发送状态:" + result.getSendStatus());
            }
            stop.stop();
            logger.info("----------------发送十条消息耗时：" + stop.getTotalTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }

    }
}
