package com.xzh.customer.technical.jpa.mongo.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoUserRepository extends MongoRepository<MongoUser, Long> {
}
