package com.xzh.customer.technical.kafka;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-12 16:31
 * @description：
 * @modified By：
 * @version:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class KafkaTest {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void kafkaTest() throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send("testTopic", "新的反序列化测试002...");
        log.info(send.get().toString());
    }
}
