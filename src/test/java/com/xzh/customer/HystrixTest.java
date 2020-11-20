package com.xzh.customer;

import com.xzh.customer.technical.springcloud.hystrix.TestHystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-26 16:59
 * @description：
 * @modified By：
 * @version:
 */
@SpringBootTest(classes = CustomerApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Slf4j
public class HystrixTest {
    @Resource
    private TestHystrixCommand hystrixCommand;

    @Test
    public void test001_execute() {
        hystrixCommand.execute();
        log.info("HystrixTest test001_execute running...");
    }

    @Test
    public void test002_queue() throws InterruptedException, ExecutionException, TimeoutException {
        Future<String> queue = hystrixCommand.queue();
        log.info("HystrixTest test002_queue running...");

        queue.get(5000, TimeUnit.MILLISECONDS);
        log.info("HystrixTest test002_queue end...");
    }

}
