package com.xzh.customer.technical.decathlon.countDown;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-25 15:35
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@Slf4j
@RequestMapping("/countDown")
public class CountDownController {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @GetMapping("/test001")
    public void test001() throws InterruptedException {
        log.info("主线程 [{}] 开始执行...", Thread.currentThread().getName());

        List<Count> list = new ArrayList<>();
        IntStream.range(0, 10).forEach(value -> list.add(new Count(value, value)));

        CountDownLatch countDownLatch = new CountDownLatch(list.size());

        list.forEach(count -> threadPoolExecutor.execute(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.setB(count.getB() + 1);
            log.info("子线程 [{}] 开始执行... a = [ {} ] , b = [ {} ]", Thread.currentThread().getName(), count.getA(), count.getB());
            countDownLatch.countDown();
        }));
        countDownLatch.await(400,TimeUnit.MILLISECONDS);
        log.info(list.toString());
        log.info("主线程 [{}] 结束执行...", Thread.currentThread().getName());
    }

    @AllArgsConstructor
    @Data
    public static class Count {
        int a;
        int b;
    }
}
