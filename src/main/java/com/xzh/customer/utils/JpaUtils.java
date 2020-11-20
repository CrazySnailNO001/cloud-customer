package com.xzh.customer.utils;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-16 17:31
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Component
public class JpaUtils<T> {

    public Specification<T> getEqualSpecification(Map<String, Object> map) {
        return (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            map.forEach((key, value) -> predicates.add(criteriaBuilder.equal(root.get(key), value)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public void putIfNotNull(Map<String, Object> map, String key, Object value) {
        if (null != value)
            map.put(key, value);
    }
}
