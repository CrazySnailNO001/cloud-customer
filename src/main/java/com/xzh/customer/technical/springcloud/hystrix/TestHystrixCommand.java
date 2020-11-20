package com.xzh.customer.technical.springcloud.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Future;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-26 13:10
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
@Slf4j
public class TestHystrixCommand extends HystrixCommand<String> {

    public TestHystrixCommand() {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("defaultGroupKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("defaultThreadPoolKey"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("defaultCommandKey")));
    }

    /**
     * Sync
     * 里面也是调用queue(),只是会执行queue().get();等待queue()方法执行完成,拿到返回值再返回
     */
    @Override
    public String execute() {
        log.info("TestHystrixCommand execute... , Thread is [ {} ]", Thread.currentThread().getName());
        return super.execute();
    }

    /**
     * Async
     */
    @Override
    public Future<String> queue() {
        log.info("TestHystrixCommand queue... , Thread is [ {} ]", Thread.currentThread().getName());
        return super.queue();
    }

    @Override
    protected String run() throws Exception {
        log.info("TestHystrixCommand run... , Thread is [ {} ]", Thread.currentThread().getName());
//        String forObject = restTemplate.getForObject("http://8762/hello?name=testName", String.class);
//        throw new NullPointerException();
        return "run method success";
//        return forObject;
    }

    @Override
    protected String getFallback() {
//        String fallback = super.getFallback();
        log.info("TestHystrixCommand getFallback... message is [ {} ], Thread is [ {} ]", "fallback", Thread.currentThread().getName());
//        return super.getFallback();
        return "网络繁忙,请稍后重试";
    }
}
