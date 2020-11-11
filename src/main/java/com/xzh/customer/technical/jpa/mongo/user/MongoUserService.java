package com.xzh.customer.technical.jpa.mongo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-05-08 17:04
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class MongoUserService {
    @Autowired
    private MongoUserRepository mongoUserRepository;

    public MongoUser findOne(Long id){
        return mongoUserRepository.findById(id).get();
    }

    public MongoUser save(MongoUser mongoUser){
        return mongoUserRepository.save(mongoUser);
    }

    public List<MongoUser> findAll(){
        return mongoUserRepository.findAll();
    }
}
