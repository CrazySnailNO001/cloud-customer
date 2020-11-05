package com.xzh.customer.technical.mybatis.service;

import com.xzh.customer.technical.mybatis.dto.User;

import java.util.List;

public interface IUserService {
    List<User> findAllUser();

    User findById(int id);
}
