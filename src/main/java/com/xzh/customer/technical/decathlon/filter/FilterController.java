package com.xzh.customer.technical.decathlon.filter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-15 20:22
 * @description：
 * @modified By：
 * @version:
 */
@RequestMapping("/filter")
@RestController
public class FilterController {
    @GetMapping("test001")
    public String test001(HttpServletRequest httpServletRequest, @RequestHeader(required = false) String AuthToken,String newParam) {
        System.out.println(httpServletRequest.toString());
        return "success";
    }
}

