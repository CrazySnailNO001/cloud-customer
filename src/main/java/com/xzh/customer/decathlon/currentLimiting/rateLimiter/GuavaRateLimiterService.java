package com.xzh.customer.decathlon.currentLimiting.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-05 15:27
 * @description：
 * @modified By：
 * @version:
 */

@Service
public class GuavaRateLimiterService {
    /*每秒控制5个许可*/
    RateLimiter rateLimiter = RateLimiter.create(5.0);

    /**
     * 获取令牌
     *
     * @return
     */
    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }

}
