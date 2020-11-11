package com.xzh.customer.technical.jpa.mongo.student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoStudentRepository extends MongoRepository<MongoStudent, Long> {
}
