package com.xzh.customer.technical.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author XZHH
 * @Description:
 * @create 2019/4/29 0029 16:43
 * @modify By:
 **/
public class Slf4jLogger {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void test(){
        logger.info("success");
    }
}
