package com.xzh.customer.technical.mq.kafka;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
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
@ConfigurationProperties(prefix = "sys.data.kafka.stcom")
@Setter
public class CustomerKafkaListeners {
    private Integer partitions;

//    @KafkaListener(id = "myContainer1",//id是消费者监听容器
////            配置topic和分区：监听两个topic，分别为topic1、topic2，
////            topic1只接收分区0，3的消息，
////            topic2接收分区0和分区1的消息，但是分区1的消费者初始位置为5
//            topicPartitions =
//                    { @TopicPartition(topic = "topic1", partitions = { "0", "3" }),
//                            @TopicPartition(topic = "topic2", partitions = "0",
//                                    partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "4"))
//                    })
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
