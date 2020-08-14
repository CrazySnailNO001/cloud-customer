package com.xzh.customer.technical.mq.rocket;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.xzh.customer.utils.Global.TAG_TEST;
import static com.xzh.customer.utils.Global.TOPIC_TEST;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/29 0029 17:01
 * @modify By:
 **/
@Service
public class RocketMQConsumer {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${apache.rocketmq.consumer.pushConsumer}")
    private String consumerGroup;

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

//    @PostConstruct
    public void defaultMQPushConsumer() {
        //消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

        //指定NameServer地址，多个地址以 ; 隔开
        consumer.setNamesrvAddr(namesrvAddr);
        try {
            //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费;如果非第一次启动，那么按照上次消费的位置继续消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.subscribe(TOPIC_TEST, TAG_TEST);

            //rocketmq消费者注册监听有两种模式，有序消费MessageListenerOrderly和并发消费MessageListenerConcurrently
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                try {
                    list.stream().forEach(messageExt -> {
//                        logger.info("messageExt: " + messageExt);   //输出消息内容
                        logger.info("消费响应：msgId : " + messageExt.getMsgId() + ",  msgBody : " + messageExt.getBody());});
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info("稍后再试");
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
                }
                logger.info("消费成功");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //消费成功
            });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
