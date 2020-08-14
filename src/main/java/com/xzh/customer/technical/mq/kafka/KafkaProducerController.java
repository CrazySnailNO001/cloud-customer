package com.xzh.customer.technical.mq.kafka;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-14 10:50
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducerController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping("/producer_test")
    public ResponseEntity kafkaProducerTest(@RequestBody String message) {
        kafkaTemplate.send(TopicConstants.TOPIC_TEST_TOPIC, message);
        return ResponseEntity.ok("success");
    }

}
