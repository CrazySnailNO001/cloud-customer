package com.xzh.customer.technical.decathlon.di;

import org.springframework.stereotype.Service;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-16 15:22
 * @description：
 * @modified By：
 * @version:
 */
@Service
public class DiService {
    public String test001(String result){
        System.out.println(result);
        return result;
    }
}
