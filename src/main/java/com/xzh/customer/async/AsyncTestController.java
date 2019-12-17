package com.xzh.customer.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-09 21:40
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@Slf4j
@RequestMapping("/async")
public class AsyncTestController {

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private ApplicationContext applicationContext;


    @GetMapping("test01")
    public void testAsync01() throws InterruptedException {
        log.info("=====01主线程执行start: " + Thread.currentThread().getName());

        asyncService.doAsync01();
        asyncService.doAsync02();

        log.info("=====01主线程执行end: " + Thread.currentThread().getName());
    }

    @GetMapping("test02")
    public void testAsync02() throws InterruptedException {
        log.info("=====02主线程执行start: " + Thread.currentThread().getName());
        AsyncTestController asyncTestController = applicationContext.getBean(AsyncTestController.class);

        asyncTestController.doAsync01();
        asyncTestController.doAsync02();

        log.info("=====02主线程执行end: " + Thread.currentThread().getName());
    }


    /**
     * create by: xzh
     * description: TransationConfig 加了事务的话, AopContext 就可以正常使用了
     *  或者 使用 MyBeanFactoryPostProcessor 设置 exposeProxy = true
     * create time: 2019-12-17 14:21
     * @Param:
     * @return void
     */
    @GetMapping("test03")
//    @Async("defaultTaskExecutor")
    public void testAsync03() throws InterruptedException {

        log.info("=====03主线程执行start: " + Thread.currentThread().getName());
        AsyncTestController asyncTestController = applicationContext.getBean(AsyncTestController.class);

        AsyncTestController currentProxy = (AsyncTestController) AopContext.currentProxy();
        if (asyncTestController == currentProxy)
            log.info("spring容器中的对象(currentProxy)与当前代理类(asyncTestController)是同一个对象");
        currentProxy.doAsync01();
        currentProxy.doAsync03();

        log.info("=====03主线程执行end: " + Thread.currentThread().getName());
    }

    @GetMapping("test04")
//    @Async("defaultTaskExecutor")
//    @Transactional
    public void testAsync04() throws InterruptedException {
        log.info("=====04主线程执行start: " + Thread.currentThread().getName());

        AsyncTestController currentProxy = (AsyncTestController) AopContext.currentProxy();
        currentProxy.doAsync01();

        log.info("=====04主线程执行end: " + Thread.currentThread().getName());
    }



    @Async("Customer-thread")
    public void doAsync01() throws InterruptedException {
        Thread.sleep(3000);
        log.info("=====子线程001执行: " + Thread.currentThread().getName());
    }

    @Async("Customer-thread")
    public void doAsync02() {
        log.info("=====子线程002执行: " + Thread.currentThread().getName());
    }

    @Async("Customer-thread")
    @Transactional
    public void doAsync03() throws InterruptedException {
        Thread.sleep(3000);
        log.info("=====子线程003执行: " + Thread.currentThread().getName());
    }
}
