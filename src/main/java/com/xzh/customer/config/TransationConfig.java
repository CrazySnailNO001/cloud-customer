package com.xzh.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-16 17:39
 * @description：
 * @modified By：
 * @version:
 */
@Component
public class TransationConfig {
//    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.xzh.customer.decathlon.async..*.*(..))";
//
//    @Autowired
//    private PlatformTransactionManager platformTransactionManager;
//
//    @Bean
//    public PlatformTransactionManager platformTransactionManager(){
//        return new PlatformTransactionManager() {
//            @Override
//            public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
//                return null;
//            }
//
//            @Override
//            public void commit(TransactionStatus transactionStatus) throws TransactionException {
//
//            }
//
//            @Override
//            public void rollback(TransactionStatus transactionStatus) throws TransactionException {
//
//            }
//        };
//    }

}
