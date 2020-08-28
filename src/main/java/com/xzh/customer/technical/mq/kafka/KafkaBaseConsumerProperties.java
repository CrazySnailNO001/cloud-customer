package com.xzh.customer.technical.mq.kafka;

import lombok.Data;

import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-14 15:24
 * @description：
 * @modified By：
 * @version:
 */
@Data
public class KafkaBaseConsumerProperties {
    private List<String> bootstrapServers;
    private String groupId;
    private int autoCommitInterval;
    private boolean enableAutoCommit;
}
