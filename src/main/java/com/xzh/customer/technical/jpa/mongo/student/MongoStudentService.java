package com.xzh.customer.technical.jpa.mongo.student;

import com.xzh.customer.utils.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
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

//    List<MongoStudent> findByCondition(Long id, String name, Integer age, String className, Instant updatedTime) {
//
//        Specification<MongoStudent> specification = getSpecification(id, name, age, className, updatedTime);
//
//        List<Sort.Order> sorts = SqlUtils.string2Sorts("asc", SqlUtils.SORT_DIRECTION_ASC);
//        List<Sort.Order> desc = SqlUtils.string2Sorts("desc", SqlUtils.SORT_DIRECTION_DESC);
//        if (null == sorts) {
//            sorts = new ArrayList<>();
//        }
//        if (null != desc) {
//            sorts.addAll(desc);
//        }
//        Pageable pageable = PageRequest.of(10, 0, Sort.by(sorts));
//
//        return mongoRepository.findAll(specification, pageable).getContent();
//    }

//    private Specification<MongoStudent> getSpecification(Long id, String name, Integer age, String className, Instant updatedTime) {
//        return (Specification<MongoStudent>) (root, criteriaQuery, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            // 排除逻辑删除数据
//            predicates.add(criteriaBuilder.equal(root.get("isDelete"), 0));
//
//            if (null != id) {
//                predicates.add(criteriaBuilder.equal(root.get("id"), id));
//            }
//            if (!StringUtils.isEmpty(name)) {
//                predicates.add(criteriaBuilder.equal(root.get("name"), name));
//            }
//            if (null != age) {
//                predicates.add(criteriaBuilder.equal(root.get("age"), age));
//            }
//            if (!StringUtils.isEmpty(className)) {
//                predicates.add(criteriaBuilder.equal(root.get("className"), className));
//            }
//
//            if (null != updatedTime) {
//                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("updateTime"), updatedTime));
//
//            }
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
}
