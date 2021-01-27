package com.xzh.customer.technical.mybatis.dao.generator;

import com.xzh.customer.technical.mybatis.dto.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    @Select("select * from user")
    List<User> findAll();
}