package com.xzh.customer.technical.myMock;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ：xzh
 * @date ：Created in 2020-06-02 11:12
 * @description：
 * @modified By：
 * @version:
 */
public interface MockService {
     String mockGetTest(@RequestParam String name);
}
