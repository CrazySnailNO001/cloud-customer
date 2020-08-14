package com.xzh.customer.technical.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-14 10:33
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@Component
public class CustomerKafkaListeners {
    @KafkaListener(topics = "testTopic")
    private void consumerTest(String message) {
        log.info("[CustomerKafkaListeners consumerTest API] get message from kafka : [ {} ]", message);
    }
}
