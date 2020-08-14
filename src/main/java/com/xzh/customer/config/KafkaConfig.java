package com.xzh.customer.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.support.ProducerListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Zhonghui
 * @Date: 7/17/2019 5:50 PM
 */
@Configuration
@Slf4j
@Setter
//@ConfigurationProperties(prefix = "biz.data.kafka")
//@ConfigurationProperties(prefix = "spring.kafka.mine.producer")
public class KafkaConfig {
    private static String server = "xzhwxx.xyz:9092";
    private static String ack = "1";
    private static String clientId = "producer_customer";

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setProducerListener(kafkaProducerListener());
        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        configs.put(ProducerConfig.ACKS_CONFIG, ack);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareListenerErrorHandler() {
        return (message, e, consumer) -> {
            log.error("consumer aware listener error is {}", e.getMessage());
            return null;
        };
    }

    @Bean
    public KafkaProducerListener kafkaProducerListener() {
        return new KafkaProducerListener();
    }

    static class KafkaProducerListener implements ProducerListener<String, String> {
        @Override
        public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
            log.error("kafka producer success,topic is [ {} ] message is [ {} ] and offset is {}", producerRecord.topic(), producerRecord.value(), recordMetadata.offset());
        }

        @Override
        public void onError(ProducerRecord producerRecord, Exception exception) {
            log.error("kafka producer error,topic is [ {} ] message is [ {} ] and error message is {}", producerRecord.topic(), producerRecord.value(), exception.getMessage());
        }

        @Override
        public void onError(String topic, Integer partition, String key, String value, Exception exception) {
            log.error("kafka producer error, topic is {}, partition is {}, key is {}, value is {}, error mess is {}",
                    topic, partition, key, value, exception.getMessage());
        }
    }
}
