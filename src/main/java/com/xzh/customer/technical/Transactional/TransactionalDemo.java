package com.xzh.customer.technical.Transactional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

/**
 * @author XZHH
 * @Description:
 * @create 2019/5/7 0007 16:33
 * @modify By:
 **/
public class TransactionalDemo {

    @Transactional(isolation = REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public void test() {

    }
}
