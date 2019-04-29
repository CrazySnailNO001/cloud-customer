package com.xzh.customer.log;
import org.apache.log4j.Logger;
/**
 * @author XZHH
 * @Description:
 * @create 2019/4/29 0029 16:46
 * @modify By:
 **/
public class Log4j {
    protected final Logger logger = Logger.getLogger(this.getClass());

    public void test(){
        logger.info("success");
    }

}
