package com.xzh.customer.technical.jpa.mysql.repostory;

import com.xzh.customer.technical.jpa.mysql.dto.MysqlUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlUserRepository extends JpaRepository<MysqlUser, Integer>, JpaSpecificationExecutor<MysqlUser> {
}
