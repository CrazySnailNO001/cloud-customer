package com.xzh.customer.technical.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-17 15:48
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/annotation")
public class AnnotationController {
    @Autowired
    private DiService diService;

    @GetMapping("/test001")
    public String test001() {
        return diService.test001("annotation");
    }
}
