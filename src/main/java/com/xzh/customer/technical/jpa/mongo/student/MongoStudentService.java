package com.xzh.customer.technical.jpa.mongo.student;

import com.xzh.customer.utils.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-09 15:25
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Service
public class MongoStudentService {
    @Resource
    private MongoStudentRepository mongoRepository;

    MongoStudent saveOrUpdate(@RequestBody MongoStudent mongoStudent) {
        if (null == mongoStudent.getId()) {
            return mongoRepository.save(mongoStudent);
        } else {
            MongoStudent toSave = findById(mongoStudent.getId());
            ObjectUtils.populateIgnoreNullField(mongoStudent,toSave);
            return mongoRepository.save(toSave);
        }
    }

    MongoStudent findById(Long id) {
        return mongoRepository.findById(id).orElseThrow();
    }

    List<MongoStudent> getAll(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        return mongoRepository.findAll(pageRequest).getContent();
    }

    Long countByCondition() {
        return mongoRepository.count();
    }


    List<MongoStudent> findByCondition(Long id, String name, Integer age, String className, Instant updatedTime) {

        Pageable pageable = PageRequest.of(10, 0);

        return mongoRepository.findAll(pageable).getContent();
    }

}
