package com.xzh.customer.technical.mybatis.controller;

import com.xzh.customer.technical.mybatis.dto.User;
import com.xzh.customer.technical.mybatis.service.IUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-05 11:52
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping(value = "/all_user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<User> findAllUsers() {
        return userService.findAllUser();
    }

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User findById(@RequestParam int id) {
        return userService.findById(id);
    }
}
