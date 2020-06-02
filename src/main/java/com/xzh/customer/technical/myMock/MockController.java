package com.xzh.customer.technical.myMock;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：xzh
 * @date ：Created in 2020-06-02 11:09
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/mock")
public class MockController {
    @Resource
    private MockService mockService;

    @GetMapping(value = "get_mock", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String mockGetTest(@RequestParam String name) {
        System.out.println("=============mockController name" + name);
        return mockService.mockGetTest(name);
    }
}
