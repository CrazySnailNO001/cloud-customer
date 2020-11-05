package com.xzh.customer.technical.mybatis.service.impl;

import com.xzh.customer.technical.mybatis.dao.generator.UserMapper;
import com.xzh.customer.technical.mybatis.dto.User;
import com.xzh.customer.technical.mybatis.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-05 11:53
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAll();
    }

    @Override
    public User findById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
