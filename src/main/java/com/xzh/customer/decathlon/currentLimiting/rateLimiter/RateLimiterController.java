package com.xzh.customer.decathlon.currentLimiting.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;
import com.xzh.customer.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-05 15:11
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/rateLimiter")
@Slf4j
public class RateLimiterController {

    @Autowired
    private GuavaRateLimiterService rateLimiterService;

    @ResponseBody
    @RateLimitAnnotation(1)
    @RequestMapping("/test001")
    public String test() {
        return "success";
    }

    @ResponseBody
    @RequestMapping("/test002")
    public String testRateLimiter() {
        if (rateLimiterService.tryAcquire())
            return "成功获取许可";
        return "未获取到许可";
    }

    /**
     * create by: xzh
     * description: 假定了每秒处理请求的速率为1个，现在我有10个任务要处理，
     * 那么RateLimiter就很好的实现了控制速率，总共10个任务，需要9次获取许可，
     * 所以最后10个任务的消耗时间为9s左右。
     * create time: 2020-01-05 15:22
     */
    @ResponseBody
    @RequestMapping("/test003")
    public void test003() {

        String start = new SimpleDateFormat(DateUtil.DATE_FORMAT_SECOND).format(new Date());
        log.info("Start time : [ {} ]", start);

        RateLimiter rateLimiter = RateLimiter.create(1);
        IntStream.range(0, 10).forEach(value -> {
            rateLimiter.acquire();
            log.info("call execute [ {} ]", value);
        });

        String end = new SimpleDateFormat(DateUtil.DATE_FORMAT_SECOND).format(new Date());
        log.info("End time : [ {} ]", end);
    }
}
