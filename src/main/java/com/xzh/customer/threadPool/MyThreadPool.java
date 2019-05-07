package com.xzh.customer.threadPool;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author XZHH
 * @Description:
 * @create 2019/5/5 0005 10:22
 * @modify By:
 **/
public class MyThreadPool {
    /**
     * 一个阻塞队列，用来存储等待执行的任务
     */
    private BlockingQueue<Runnable> workQueue;
    /**
     * 线程工厂，主要用来创建线程；
     */
    private volatile ThreadFactory threadFactory;
    /**
     * 表示当拒绝处理任务时的策略
     */
    private volatile RejectedExecutionHandler handler;
    /*
     * 表示线程没有任务执行时最多保持多久时间会终止
     */
    private volatile long keepAliveTime;
    /*
     * 核心池的大小,默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
     */
    private volatile int corePoolSize;
    /*
     * 线程池最大线程数，它表示在线程池中最多能创建多少个线程；
     */
    private volatile int maximumPoolSize;

    public void MyThreadPool(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,    /*参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：
                                                TimeUnit.DAYS;               //天
                                                TimeUnit.HOURS;             //小时
                                                TimeUnit.MINUTES;           //分钟
                                                TimeUnit.SECONDS;           //秒
                                                TimeUnit.MILLISECONDS;      //毫秒
                                                TimeUnit.MICROSECONDS;      //微妙
                                                TimeUnit.NANOSECONDS;       //纳秒*/
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
}
