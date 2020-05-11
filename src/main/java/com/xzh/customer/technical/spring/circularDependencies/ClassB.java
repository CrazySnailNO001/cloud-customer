package com.xzh.customer.technical.spring.circularDependencies;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：xzh
 * @date ：Created in 2020-05-04 14:34
 * @description：
 * @modified By：
 * @version:
 */
@Component
@Data
public class ClassB {
    public ClassB() {
//        this.classA = classA;
    }

    private ClassA classA = new ClassA();
}
