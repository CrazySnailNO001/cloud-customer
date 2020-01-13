package com.xzh.customer.decathlon.currentLimiting.redisLimiter;

import com.xzh.customer.decathlon.currentLimiting.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-02 17:24
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class AccessLimitInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (!method.isAnnotationPresent(AccessLimit.class)) {
                return true;
            }
            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int limit = accessLimit.limit();
            int sec = accessLimit.sec();
            String key = request.getRequestURI();
            try {
                Integer maxLimit = redisTemplate.opsForValue().get(key);
                if (maxLimit == null) {
                    //set时一定要加过期时间
                    redisTemplate.opsForValue().set(key, 1, sec, TimeUnit.SECONDS);
                } else if (maxLimit < limit) {
                    //方案一:
//                    redisTemplate.opsForValue().set(key, maxLimit + 1, Objects.requireNonNull(redisTemplate.getExpire(key)), TimeUnit.SECONDS);

                    //方案二:
                    Date date = new Date(Instant.now().toEpochMilli() + Objects.requireNonNull(redisTemplate.getExpire(key)) * 1000);
                    redisTemplate.opsForValue().set(key, maxLimit + 1);
                    redisTemplate.expireAt(key, date);

                } else {
                    ResponseUtil.addResponse(response, "请求过于频繁,请稍后再试!");
                    return false;
                }
            } catch (NullPointerException e) {
                redisTemplate.opsForValue().set(key, 1, sec, TimeUnit.SECONDS);
            }
        }
        return true;

    }
}
