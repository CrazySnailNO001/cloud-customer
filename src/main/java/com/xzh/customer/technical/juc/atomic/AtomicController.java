package com.xzh.customer.technical.juc.atomic;

import com.xzh.customer.common.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-31 14:44
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/atomic")
@Slf4j
public class AtomicController {
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    private volatile Integer intNumber;//volatile 不保证原子性
    private AtomicInteger atomicInteger;
    private List<Integer> list;


    @PostMapping(value = "/list_test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ApiResponseDto> test01() throws ExecutionException, InterruptedException {
        list = new ArrayList<>();

        return getApiResponseDtoResponseEntity(list);
    }

    @PostMapping(value = "/safe_list_test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ApiResponseDto> test00() throws ExecutionException, InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        Vector list2 = new Vector();

        return getApiResponseDtoResponseEntity(list);
    }

    private ResponseEntity<ApiResponseDto> getApiResponseDtoResponseEntity(List<Integer> list) throws InterruptedException, ExecutionException {
        Future<?> submit = threadPoolExecutor.submit(() -> {
            for (int i = 1; i <= 100000; i++) {
                list.add(i);
            }
        });

        Future<?> submit2 = threadPoolExecutor.submit(() -> {
            for (int i = 1; i <= 100000; i++) {
                list.add(i);
            }
        });

        Future<?> submit3 = threadPoolExecutor.submit(() -> {
            for (int i = 1; i <= 100000; i++) {
                list.add(i);
            }
        });
        Object o = submit.get();
        Object o1 = submit2.get();
        Object o2 = submit3.get();
        ApiResponseDto apiResponseDto = ApiResponseDto.success(list.size());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    @PostMapping(value = "/int_test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ApiResponseDto> test02() throws InterruptedException {
        intNumber = 0;
        atomicInteger = new AtomicInteger(0);

        List<Thread> threadList = new ArrayList<>(600);
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        count();
//                        safeCount(atomicInteger);
                        atomicInteger.incrementAndGet();
                    }
                }
            });
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        /**
         * 给时间让所有线程执行完成
         */
        Thread.sleep(100);

        ApiResponseDto apiResponseDto = ApiResponseDto.success("intNumber:" + intNumber + ", atomicInteger:" + atomicInteger.get());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }

    /**
     * 使用CAS实现线程安全的计数器
     */
    private void safeCount(AtomicInteger atomicInteger) {
        for (; ; ) {
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    /**
     * 非线程安全的计数器
     */
    private void count() {
        intNumber++;
    }


}
