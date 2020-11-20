package com.xzh.customer.technical.springcloud.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import rx.Observable;

import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-26 18:15
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
@Slf4j
public class TestHystrixObservableCommand extends HystrixObservableCommand<String> {

    protected TestHystrixObservableCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("testObservableCommandKey")));
    }

    /**
     * 返回值是一个事件源
     */
    @Override
    protected Observable<String> construct() {
        log.info("TestHystrixObservableCommand construct... , Thread is [ {} ]", Thread.currentThread().getName());

        return Observable.create(subscriber -> {
            log.info("TestHystrixObservableCommand construct.call start... , Thread is [ {} ]", Thread.currentThread().getName());
            IntStream.range(0, 10).forEach(System.out::println);
            log.info("TestHystrixObservableCommand construct.call end... , Thread is [ {} ]", Thread.currentThread().getName());
            subscriber.onCompleted();
        });
    }

    @Override
    protected Observable<String> resumeWithFallback() {
        log.info("TestHystrixObservableCommand resumeWithFallback... , Thread is [ {} ]", Thread.currentThread().getName());

        return super.resumeWithFallback();
    }
}
