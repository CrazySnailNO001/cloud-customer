package com.xzh.customer.technical.spring.authInterceptor;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：xzh
 * @date ：Created in 2020-03-11 16:03
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/auth")
@Log4j
public class AuthController {
    @RequestMapping("/test001")
    public String test001(String name, HttpServletRequest httpRequest) {

        return "test001 success";
    }

    @RequestMapping("/test002")
    public String test002(String name, HttpServletRequest httpRequest) {

        return "test002 success";
    }

}
