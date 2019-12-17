package com.xzh.customer.config;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XZHH
 * @Description:    负载均衡
 * @create 2019/4/28 0028 17:01
 * @modify By:
 **/
@Configuration
public class LoadBalanced {
    @Bean
    public IRule ribbonRule() {
        // 升级:平滑权重
        /**轮训  在这个策略下 熔断会一直走fallback,猜想:熔断相当于没有请求,一直在原地踏步,故相当于轮训的时候当前服务一直没有过去  */
//        return new RoundRobinRule();
//         return new WeightedResponseTimeRule();    //根据响应时间分配一个weight(权重)，响应时间越长，weight越小，被选中的可能性越低
        //return new RetryRule();                    //带有重试机制的轮训,对选定的负载均衡策略机上重试机制，在一个配置时间段内当选择server不成功，则一直尝试使用subRule的方式选择一个可用的server
        return new RandomRule();                   //随机
        //return new TestRule();                     //自定义规则
    }
}
