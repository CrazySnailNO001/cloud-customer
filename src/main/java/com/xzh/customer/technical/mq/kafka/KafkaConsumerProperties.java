package com.xzh.customer.technical.mq.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-14 15:24
 * @description：
 * @modified By：
 * @version:
 */
@ConfigurationProperties(prefix = "sys.data.kafka.consumer.default")
public class KafkaConsumerProperties extends KafkaBaseConsumerProperties{
}
