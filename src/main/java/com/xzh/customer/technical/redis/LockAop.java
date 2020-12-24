package com.xzh.customer.technical.redis;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

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

    @Pointcut("@annotation(com.xzh.customer.technical.redis.DistributedLock)")
    public void distributedAop() {
    }

    @Around(value = "distributedAop()")
    public Object aroundApi(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock annotation = method.getAnnotation(DistributedLock.class);
        String value = annotation.value();
        RedissonClient client = Redisson.create();
        RLock lock = client.getLock(value);
        lock.lock();
        log.info("[LockAop aroundApi] {} 上锁啦...", lock);

        try {
            return joinPoint.proceed();
        } finally {
            lock.unlock();
            log.info("[LockAop aroundApi] {} 解锁啦...", lock);
        }
    }

}
