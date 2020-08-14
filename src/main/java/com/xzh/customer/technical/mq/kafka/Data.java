package com.xzh.customer.technical.mq.kafka;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author ：xzh
 * @date ：Created in 2020-08-14 11:45
 * @description：
 * @modified By：
 * @version:
 */
@lombok.Data
public class Data {
    @Value("${biz.data.kafka.server}")
    public static String server;

}
