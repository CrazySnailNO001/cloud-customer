package com.xzh.customer.utils;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author ：xzh
 * @date ：Created in 2020-12-24 16:29
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Component
public class CurrentTimeUtil implements ApplicationRunner {
    private long time;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            Thread.sleep(5);
            Instant now = Instant.now();
            time = now.getEpochSecond();
        }
    }

    public long getTime() {
        return time == 0 ? Instant.now().getNano() : time;
    }
}
