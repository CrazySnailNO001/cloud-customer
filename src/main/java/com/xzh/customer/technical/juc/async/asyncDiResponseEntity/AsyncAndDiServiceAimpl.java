package com.xzh.customer.technical.juc.async.asyncDiResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-17 11:02
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class AsyncAndDiServiceAimpl implements AsyncAndDiServiceA {
    @Autowired
    private AsyncAndDiServiceB asyncAndDiServiceB;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public String test() {

        threadPoolExecutor.execute(() -> {
            String result = asyncAndDiServiceB.test();
            System.out.println("===========" + result);
        });
        return "result";
    }
}
