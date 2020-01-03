package com.xzh.customer.decathlon.currentLimiting.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

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
public class AccessLimitInterceptor implements HandlerInterceptor {
    @Autowired
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
//            String key = IPUtil.getIpAddr(request) + request.getRequestURI();
            String key = "111";
            Integer maxLimit = redisTemplate.opsForValue().get(key);
            if (maxLimit == null) {
                //set时一定要加过期时间
                redisTemplate.opsForValue().set(key, 1, sec, TimeUnit.SECONDS);
            } else if (maxLimit < limit) {
                redisTemplate.opsForValue().set(key, maxLimit + 1, sec, TimeUnit.SECONDS);
            } else {
                output(response, "请求太频繁!");
                return false;
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
