package com.xzh.customer.technical.juc.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-16 15:28
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/thread")
@Slf4j
public class ThreadPoolController {
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private TaskExecutor taskExecutor;

    @RequestMapping("/test001")
    public void test001() {
        log.info("线程 : [ {} ] is running", Thread.currentThread().getName());

        threadPoolExecutor.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("线程 : [ {} ] is running", Thread.currentThread().getName());
        });

        taskExecutor.execute(() -> {
            log.info("线程 : [ {} ] is running", Thread.currentThread().getName());

        });
    }

    @RequestMapping("/test002")
    public void test002() throws InterruptedException, ExecutionException, TimeoutException {
        log.info("主线程 : [ {} ] is running", Thread.currentThread().getName());

        Future<?> submit = threadPoolExecutor.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("子线程01 : [ {} ] is running", Thread.currentThread().getName());
        });

        threadPoolExecutor.execute(() -> {
            log.info("子线程02 : [ {} ] is running", Thread.currentThread().getName());
        });

        threadPoolExecutor.execute(() -> {
            try {

                //等2000ms,如果子线程02没执行完,那么就直接执行
                submit.get(2000, TimeUnit.MILLISECONDS);
                log.info("子线程03 : [ {} ] is running", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        });

        //等4000ms,如果子线程02没执行完,那么就直接执行
        submit.get(4000, TimeUnit.MILLISECONDS);
        log.info("主线程 : [ {} ] is running", Thread.currentThread().getName());
        submit.get(2000, TimeUnit.MILLISECONDS);
    }

    @RequestMapping("/test003")
    public void test003(){

        IntStream.range(0,10).forEach(value -> {
            threadPoolExecutor.submit(() -> {
                try {
                    log.info("子线程 : [ {} ] is start running 任务 [ {} ]", Thread.currentThread().getName(),value);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("子线程 : [ {} ] is end running 任务 [ {} ]", Thread.currentThread().getName(),value);
            });
        });

    }

    private static SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        int corePoolSize = 1;
        int maximumPoolSize = 1;
        BlockingQueue queue = new  ArrayBlockingQueue<Runnable>(1);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,  maximumPoolSize,
                0, TimeUnit.SECONDS, queue ) ;
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy ());
        for(int i=0;i<10;i++){
            final int index = i;
            pool.submit(new Runnable(){

                @Override
                public void run() {
                    log(Thread.currentThread().getName()+" begin run task :"+index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log(Thread.currentThread().getName()+" finish run  task :"+index);
                }

            });
        }

        log("main thread before sleep!!!");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("before shutdown()");

        pool.shutdown();

        log("after shutdown(),pool.isTerminated=" + pool.isTerminated());
        try {
            pool.awaitTermination(1000L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log("now,pool.isTerminated=" + pool.isTerminated());
    }

    protected static void log(String string) {
        System.out.println(sdf.format(new Date())+"  "+string);
    }

}
