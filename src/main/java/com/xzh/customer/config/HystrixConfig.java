//package com.xzh.customer.config;
//
//import com.netflix.hystrix.*;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author ：xzh
// * @date ：Created in 2020-03-25 11:49
// * @description：不起作用,不知道为啥
// * @modified By：
// * @version:
// */
//@Configuration
//public class HystrixConfig extends HystrixCommand<String> {
//    /**
//     * HystrixCommandKey是Hystrix命令的唯一标识，准确来说是HystrixCommand实例或者HystrixObservableCommand实例的唯一标识。它是必须的，如果不自定义配置，它会通过下面方式确定默认值：
//     * [HystrixCommand或者HystrixObservableCommand的具体子类].getClass().getSimpleName();
//     * 注意一点：大部分Hystrix的配置都是和HystrixCommandKey绑定，所以HystrixCommandKey是比较重要的。
//     *
//     * HystrixCommandGroupKey是用于对Hystrix命令进行分组，分组之后便于统计展示于仪表盘、上传报告和预警等等，也就是说，HystrixCommandGroupKey是Hystrix内部进行度量统计时候的分组标识，
//     * 数据上报和统计的最小维度就是分组的KEY。HystrixCommandGroupKey是必须配置的
//     *
//     * HystrixThreadPoolKey主要标识用于监控、度量和缓存等等作用的HystrixThreadPool实例。一个HystrixCommand会和一个独立的HystrixThreadPool实例关联，
//     * 也就是说一类HystrixCommand总是在同一个HystrixThreadPool实例中执行。如果不显式配置HystrixThreadPoolKey，那么会使用HystrixCommandGroupKey的值去配置HystrixThreadPoolKey。
//     */
//
//    /**
//     * 使用HystrixCommand的时候建议用THREAD策略，使用HystrixObservableCommand的时候建议使用SEMAPHORE策略。
//     * 使用THREAD策略让HystrixCommand在线程中执行可以提供额外的保护层，以防止因为网络超时导致的延时失败。
//     * 一般情况下，只有这种特殊例子下HystrixCommand会搭配SEMAPHORE策略使用：调用的频次太高(例如每个实例每秒数百次调用)，
//     * 这种情况如果选用THREAD策略有可能导致超过线程隔离的上限(有可能需要太多的线程或者命令太多线程不足够用于隔离请求)，这种情况一般是非网络请求调用。
//     */
//    public HystrixConfig() {
//        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HystrixConfigTestGroup"))
//                .andCommandKey(HystrixCommandKey.Factory.asKey("HystrixConfigTestKey"))
//                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HystrixConfigTestThreadKey"))
//                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
//                        .withCoreSize(20)
//                        .withMaximumSize(50)
//                        .withMaxQueueSize(50)
//                        .withKeepAliveTimeMinutes(1))
//                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
//                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
//                        .withExecutionTimeoutInMilliseconds(50)
//                        .withFallbackIsolationSemaphoreMaxConcurrentRequests(2000)));
//    }
//
//    protected HystrixConfig(HystrixCommandGroupKey group) {
//        super(group);
//    }
//
//    protected HystrixConfig(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool) {
//        super(group, threadPool);
//    }
//
//    protected HystrixConfig(HystrixCommandGroupKey group, int executionIsolationThreadTimeoutInMilliseconds) {
//        super(group, executionIsolationThreadTimeoutInMilliseconds);
//    }
//
//    protected HystrixConfig(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool, int executionIsolationThreadTimeoutInMilliseconds) {
//        super(group, threadPool, executionIsolationThreadTimeoutInMilliseconds);
//    }
//
//    protected HystrixConfig(Setter setter) {
//        super(setter);
//    }
//
//    @Override
//    protected String run() throws Exception {
//        return null;
//    }
//
//    /**
//     * 服务降级方法，当调用服务发生异常时，会调用该降级方法
//     */
//    @Override
//    protected String getFallback() {
//        System.out.println("[HystrixConfig API] fallback");
//        return super.getFallback();
//    }
//}
