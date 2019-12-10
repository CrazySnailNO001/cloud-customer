package com.xzh.customer.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-09 21:41
 * @description：
 * @modified By：
 * @version:
 */
@Service
@Slf4j
public class AsyncService {
    @Async("defaultTaskExecutor")
    public void doAsync01() throws InterruptedException {
        Thread.sleep(3000);
        log.info("=====子线程001执行: " + Thread.currentThread().getName());
    }

    @Async("defaultTaskExecutor")
    public void doAsync02() {
        log.info("=====子线程002执行: " + Thread.currentThread().getName());
    }
}
