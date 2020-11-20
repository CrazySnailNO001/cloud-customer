package com.xzh.customer.technical;

import com.xzh.customer.CustomerApplication;
import com.xzh.customer.technical.springboot.MyConditionBeanConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-24 17:37
 * @description：
 * @modified By：
 * @version:
 */
@SpringBootTest(classes = CustomerApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Slf4j
public class ConditionTest {
    @Autowired(required = false)
    private MyConditionBeanConfig.ClassAA classA;
    @Autowired(required = false)
    private MyConditionBeanConfig.ClassBB classB;
    @Autowired(required = false)
    private MyConditionBeanConfig.ClassC classC;
    @Autowired(required = false)
    private MyConditionBeanConfig.ClassD classD;
    @Autowired(required = false)
    private MyConditionBeanConfig.ClassE classE;

    @Test
    public void test() {
        log.info("classA: {}", classA);
        log.info("classB: {}", classB);
        log.info("classC: {}", classC);
        log.info("classD: {}", classD);
        log.info("classE: {}", classE);
    }
}
