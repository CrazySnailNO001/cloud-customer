package com.xzh.customer.technical.junitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/29 0029 14:19
 * @modify By:
 **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class MytestTest {
    //无法注入
    @Autowired
    private Mytest mytest;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void soutTest() throws Exception {
        mytest.soutTest();
    }

    @Test(timeout = 1000)   //超过1s就不通过
    public void testTimeout() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(2);
        System.out.println("Complete");
    }

    @Test(expected = NullPointerException.class)    //不抛NullPointerException就不通过
    public void testNullException() {
        throw new NullPointerException();
    }

}