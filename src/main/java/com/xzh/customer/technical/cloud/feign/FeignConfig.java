package com.xzh.customer.technical.cloud.feign;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import feign.Feign;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import feign.hystrix.SetterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XZHH
 * @Description:
 * @create 2019/5/5 0005 17:08
 * @modify By:
 **/
@Configuration
public class FeignConfig {
    //Feign在远程调用失败后会进行重试
    @Bean
    public Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;//不重试

        //只有在feign调用出现问题时才会触发,如 feign.client.config.provider-service.connectTimeout 导致的超时,会触发,
        //而 hystrix.command超时则不会触发

//        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 5);
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("username", "password");
    }

    @Bean
    public SetterFactory setterFactory() {
        return (target, method) -> {

            String groupKey = target.name();
            String commandKey = Feign.configKey(target.type(), method);

            HystrixCommandProperties.Setter setter = HystrixCommandProperties.Setter()
                    //设置任务执行超时时间
                    .withExecutionTimeoutInMilliseconds(7000)
                    //设置统计指标时间窗口
                    .withMetricsRollingStatisticalWindowInMilliseconds(1000 * 10)
                    //超过失败率阈值打开断路器
                    .withCircuitBreakerErrorThresholdPercentage(20)
                    //操作5个开启短路器
                    .withCircuitBreakerRequestVolumeThreshold(5)
                    //设置隔离方式
                    .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                    //设置断路器的开启持续时间
                    .withCircuitBreakerSleepWindowInMilliseconds(1000 * 30);

            return HystrixCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
                    .andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
                    .andCommandPropertiesDefaults(setter);
        };
    }
}
