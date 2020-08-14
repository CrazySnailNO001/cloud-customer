package com.xzh.customer.technical.mq.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-14 15:24
 * @description：
 * @modified By：
 * @version:
 */
@ConfigurationProperties(prefix = "spring.kafka.mine.consumer")
@Data
public class KafkaConsumerProperties {
    private List<String> bootstrapServers;
    private String groupId;
    private int autoCommitInterval;
    private boolean enableAutoCommit;
}
