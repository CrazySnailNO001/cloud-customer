package com.xzh.customer.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XZHH
 * @Description:    feign实现服务调用
 * @create 2019/4/28 0028 14:51
 * @modify By:
 **/

@RestController
public class FeignController {
    @Autowired
    private FeignRemote feignRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return feignRemote.hello(name);
    }
}
