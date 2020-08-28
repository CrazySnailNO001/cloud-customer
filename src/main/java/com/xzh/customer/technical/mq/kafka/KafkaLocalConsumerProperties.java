package com.xzh.customer.technical.mq.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-14 15:24
 * @description：
 * @modified By：
 * @version:
 */
@ConfigurationProperties(prefix = "spring.kafka.mine.consumer.local")
public class KafkaLocalConsumerProperties extends KafkaBaseConsumerProperties{
}
