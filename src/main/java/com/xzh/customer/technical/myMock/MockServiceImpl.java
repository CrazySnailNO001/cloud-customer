package com.xzh.customer.technical.myMock;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ：xzh
 * @date ：Created in 2020-06-02 11:30
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class MockServiceImpl implements MockService {
    @Override
    public String mockGetTest(@RequestParam String name) {
        System.out.println("=============mockService name" + name);
        return "name: " + name;
    }
}
