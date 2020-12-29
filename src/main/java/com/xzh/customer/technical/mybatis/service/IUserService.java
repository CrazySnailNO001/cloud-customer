package com.xzh.customer.technical.mybatis.service;

import com.github.pagehelper.PageInfo;
import com.xzh.customer.technical.mybatis.dto.User;

public interface IUserService {
    User findById(int id);

    int add(User user);

    PageInfo findAllUser(Integer pageNum, Integer pageSize);
}
