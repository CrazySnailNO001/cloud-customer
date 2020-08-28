package com.xzh.customer.technical.mq.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-14 15:24
 * @description：
 * @modified By：
 * @version:
 */
@ConfigurationProperties(prefix = "biz.data.kafka.producer")
@Data
public class KafkaProducerProperties {
    private String bootstrapServers;
    private String acks;
    private String clientId;
}
