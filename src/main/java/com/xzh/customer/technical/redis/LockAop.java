package com.xzh.customer.technical.redis;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author ：xzh
 * @date ：Created in 2020-12-24 16:49
 * @description： 自定义分布式锁AOP
 * @modified By：xzh
 * @version: V1.0.0
 */
@Aspect
@Configuration
@Slf4j
public class LockAop {
    @Resource
    private RedissonClient singleRedisSon;

    @Pointcut("@annotation(com.xzh.customer.technical.redis.DistributedLock)")
    public void distributedAop() {
    }

    @Around(value = "distributedAop()")
    public Object aroundApi(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock annotation = method.getAnnotation(DistributedLock.class);
        RLock lock = singleRedisSon.getLock(annotation.value());
        lock.tryLock(annotation.time(), TimeUnit.SECONDS);
        log.info("[LockAop aroundApi] {} 上锁啦...", lock.getName());
        try {
            return joinPoint.proceed();
        } finally {
            lock.unlock();
            log.info("[LockAop aroundApi] {} 解锁啦...", lock.getName());
            //new 出来的client才需要shutdown,注入的如果shutdown会报错:RedissonShutdownException: Redisson is shutdown
//            singleRedisSon.shutdown();
        }
    }
}
