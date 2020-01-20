package com.xzh.customer.decathlon.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-16 15:22
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/setter")
public class SetterController {
    private DiService diService;

    @Autowired
    public void setDiService(DiService diService) {
        this.diService = diService;
    }

    @GetMapping("/test001")
    public String test001() {
        return diService.test001("setter");
    }
}
