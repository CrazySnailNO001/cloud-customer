package com.xzh.customer.technical.jpa.mysql.service;

import com.xzh.customer.technical.jpa.mysql.dto.MysqlUser;
import com.xzh.customer.technical.jpa.mysql.repostory.MysqlUserRepository;
import com.xzh.customer.technical.redis.DistributedLock;
import com.xzh.customer.utils.DefaultUtils;
import com.xzh.customer.utils.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-09 16:52
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Service
public class MysqlUserService {
    @Resource
    private MysqlUserRepository repository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public MysqlUser save(MysqlUser user) {
        MysqlUser toSave = new MysqlUser();
        ObjectUtils.populateIgnoreNullField(user, toSave);
        return repository.save(toSave);
    }

    @DistributedLock(value = "mysql_user_lock")
    public List<MysqlUser> findByCondition(Long id, String name, Integer age, String className, Integer pageNUm, Integer pageSize) {
        Specification<MysqlUser> specification = getSpecification(id, name, age, className);
        Pageable pageable = PageRequest.of(DefaultUtils.valuationForNull(pageNUm, 0), DefaultUtils.valuationForNull(pageSize, 0));
        return repository.findAll(specification, pageable).getContent();
    }

    private Specification<MysqlUser> getSpecification(Long id, String name, Integer age, String className) {
        Map<String, Object> map = new HashMap<>();
        DefaultUtils.putIfNotNull(map, "id", id);
        DefaultUtils.putIfNotNull(map, "name", name);
        DefaultUtils.putIfNotNull(map, "age", age);
        DefaultUtils.putIfNotNull(map, "className", className);

        return (Specification<MysqlUser>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // 排除逻辑删除数据
            predicates.add(criteriaBuilder.equal(root.get("isDelete"), 0));
            map.forEach((key, value) -> predicates.add(criteriaBuilder.equal(root.get(key), value)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
