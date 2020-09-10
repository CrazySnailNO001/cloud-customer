package com.xzh.customer.config;

import com.xzh.customer.technical.mq.kafka.KafkaBaseConsumerProperties;
import com.xzh.customer.technical.mq.kafka.KafkaConsumerProperties;
import com.xzh.customer.technical.mq.kafka.KafkaLocalConsumerProperties;
import com.xzh.customer.technical.mq.kafka.KafkaProducerProperties;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
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
@EnableConfigurationProperties({KafkaProducerProperties.class, KafkaConsumerProperties.class, KafkaLocalConsumerProperties.class})
public class KafkaConfig {
    private final KafkaProducerProperties producerProperties;
    private final KafkaConsumerProperties defaultConsumerProperties;
    private final KafkaLocalConsumerProperties localConsumerProperties;

    public KafkaConfig(KafkaProducerProperties kafkaProperties, KafkaConsumerProperties kafkaConsumerProperties, KafkaLocalConsumerProperties kafkaLocalConsumerProperties) {
        this.producerProperties = kafkaProperties;
        this.defaultConsumerProperties = kafkaConsumerProperties;
        this.localConsumerProperties = kafkaLocalConsumerProperties;
    }

//==================================================Producer Config==============================================================================================

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
        kafkaTemplate.setProducerListener(kafkaProducerListener());
        return kafkaTemplate;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerProperties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.CLIENT_ID_CONFIG, producerProperties.getClientId());
        configs.put(ProducerConfig.ACKS_CONFIG, producerProperties.getAcks());
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public ConsumerAwareListenerErrorHandler consumerAwareListenerErrorHandler() {
        return (message, e, consumer) -> {
            log.error("consumer aware listener error is {}", e.getMessage());
            return null;
        };
    }

    private KafkaProducerListener kafkaProducerListener() {
        return new KafkaProducerListener();
    }

    static class KafkaProducerListener implements ProducerListener<String, String> {
        @Override
        public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
            log.error("kafka producer send message success,topic is [ {} ] message is [ {} ] and offset is {}", producerRecord.topic(), producerRecord.value(), recordMetadata.offset());
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

    //==================================================Consumer Config==============================================================================================

    @Bean
    @Primary
    public ConsumerFactory<Object, Object> defaultConsumerFactory() {
        return createConsumerFactory(defaultConsumerProperties);
    }

    @Bean
    public ConsumerFactory<Object, Object> localConsumerFactory() {
        return createConsumerFactory(localConsumerProperties);
    }

    private DefaultKafkaConsumerFactory<Object, Object> createConsumerFactory(KafkaBaseConsumerProperties kafkaConsumerProperties) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerProperties.getGroupId());
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerProperties.getBootstrapServers());
        configs.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kafkaConsumerProperties.getAutoCommitInterval());
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaConsumerProperties.isEnableAutoCommit());
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerProperties.getAutoOffsetReset());
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaConsumerProperties.getValueDeserializer());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaConsumerProperties.getKeyDeserializer());

        if (!StringUtils.isEmpty(kafkaConsumerProperties.getSchemaRegistryUrl()))
            configs.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, kafkaConsumerProperties.getSchemaRegistryUrl());
//        configs.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        configs.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 50);
        return new DefaultKafkaConsumerFactory<>(configs);
    }


    //不取这个名字 KafkaAnnotationDrivenConfiguration会实例化一个出来,它实例化的时候没有指定ConsumerFactory,但是上面定义了两个,如果两个都不加@Primary会报错
    @Bean
    @Primary
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<Object, Object> consumerFactory) {
        return createListenerContainerFactory(consumerFactory, defaultConsumerProperties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> localListenerContainerFactory(
            @Qualifier("localConsumerFactory") ConsumerFactory<Object, Object> consumerFactory) {
        return createListenerContainerFactory(consumerFactory, localConsumerProperties);
    }

    private ConcurrentKafkaListenerContainerFactory<String, String> createListenerContainerFactory(ConsumerFactory<Object, Object> consumerFactory, KafkaBaseConsumerProperties properties) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(properties.getConcurrency());
        factory.setConsumerFactory(consumerFactory);
        factory.setAutoStartup(properties.isEnableAutoStartup());
        return factory;
    }

    //==================================================Config Description==============================================================================================

    /*
      auto.offset.reset:
       earliest :(推荐)当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费.设置该参数后 kafka出错后重启，找到未消费的offset可以继续消费.
       latest   :(默认值)当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据,容易丢失消息，假如kafka出现问题，还有数据往topic中写，这个时候重启kafka，这个设置会从最新的offset开始消费,中间出问题的哪些就不管了。
       none     :topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常.没有用过，兼容性太差，经常出问题.
     */
}
