package com.xzh.customer.technical.jpa.mysql.controller;

import com.xzh.customer.technical.jpa.mysql.dto.MysqlUser;
import com.xzh.customer.technical.jpa.mysql.service.MysqlUserService;
import com.xzh.customer.technical.redis.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-09 16:52
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@RestController
@RequestMapping("jpa_mysql")
@Slf4j
public class MysqlUserController {
    @Resource
    private MysqlUserService mysqlUserService;

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public MysqlUser save(@RequestBody MysqlUser user) {
        return mysqlUserService.save(user);
    }

    @DistributedLock(value = "mysql_user_lock")
    @GetMapping(value = "user_condition", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MysqlUser> findByCondition(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "class_name", required = false) String className,
            @RequestParam(name = "page_num", required = false) Integer pageNum,
            @RequestParam(name = "page_size", required = false) Integer pageSize) {

        return mysqlUserService.findByCondition(id, name, age, className, pageNum, pageSize);
    }
}
