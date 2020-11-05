package com.xzh.customer.technical.mq.kafka;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-14 10:33
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "biz.data.kafka.stcom")
@Setter
public class CustomerKafkaListeners {
    private Integer partitions;

//    @KafkaListener(topics = "testTopic")
    private void defaultTest(String message) {
        System.out.println(partitions);
        log.info("[DefaultKafkaConsumer Test API] get message from kafka : [ {} ]", message);
    }

//    @KafkaListener(topics = "Topic001",groupId = "ConsumerRecords",containerFactory = "batchListenerContainerFactory")
    private void defaultTest(List<String> messages) {
        log.info("[DefaultKafkaConsumer Test API] get message from kafka : [ {} ]", messages);
    }

    //    @KafkaListener(topics = "Topic001",groupId = "ConsumerRecord")
    private void defaultTest(ConsumerRecord message) {
        log.info("[DefaultKafkaConsumer Test API] get message from kafka : [ {} ]", message);
    }


    //    @KafkaListener(topics = "testTopic", containerFactory = "localListenerContainerFactory",concurrency = "3",autoStartup = "true")
//    @KafkaListener(topics = "stockmovements", containerFactory = "localListenerContainerFactory")
    private void localTest(String message) {
        log.info("[LocalKafkaConsumer Test API] get message from kafka : [ {} ]", message);
    }
}
