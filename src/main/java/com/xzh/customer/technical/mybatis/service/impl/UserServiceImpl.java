package com.xzh.customer.technical.mybatis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public int add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public PageInfo findAllUser(Integer pageNum, Integer pageSize) {
        //自动的对PageHelper.startPage 方法下的第一个sql 查询进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public User findById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
