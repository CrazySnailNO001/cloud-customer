package com.xzh.customer.decathlon.currentLimiting.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
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
                Integer maxLimit = (Integer) redisTemplate.opsForValue().get(key);
                if (maxLimit == null) {
                    //set时一定要加过期时间
                    redisTemplate.opsForValue().set(key, 1, sec, TimeUnit.SECONDS);
                } else if (maxLimit < limit) {
                    redisTemplate.opsForValue().set(key, maxLimit + 1, sec, TimeUnit.SECONDS);
                } else {
                    output(response, "请求太频繁!");
                    return false;
                }
            } catch (NullPointerException e) {
                redisTemplate.opsForValue().set(key, 1, sec, TimeUnit.SECONDS);
            }
        }
        return true;

    }

    public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
}
