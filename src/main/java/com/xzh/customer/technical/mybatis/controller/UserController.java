package com.xzh.customer.technical.mybatis.controller;

import com.github.pagehelper.PageInfo;
import com.xzh.customer.common.ApiResponseDto;
import com.xzh.customer.technical.mybatis.dto.User;
import com.xzh.customer.technical.mybatis.service.IUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-05 11:52
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@RestController
@RequestMapping("/mybatis")
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping(value = "/all_user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageInfo findAllUsers(@RequestParam(name = "page_num", required = false) Integer pageNum,
                                 @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User findById(@RequestParam int id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseDto insert(@RequestBody User user) {
        return ApiResponseDto.success(userService.add(user));
    }
}
