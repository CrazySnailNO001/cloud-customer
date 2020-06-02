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
public class MyTestTest {
    //无法注入
    @Autowired
    private MyTest mytest;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void printTestTest() {
        mytest.printTest();
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