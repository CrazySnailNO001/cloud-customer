package com.xzh.customer.technical.decathlon.jdkHttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@Slf4j
public class JdkHttpClientControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${appClient.oms.callback.url}")
    private String omsCallbackUrl;

    @Autowired
    private RestTemplate restTemplate;

}