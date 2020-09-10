package com.xzh.customer.technical.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author ：xzh
 * @date ：Created in 2020-09-10 09:34
 * @description： ApplicationRunner demo
 * @modified By：xzh
 * @version: V1.0.0
 */
@Component
@Slf4j
public class MyRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        log.info("参数: {}", args);
        log.info("ApplicationRunner测试类 MyRunner running...");
    }
}
