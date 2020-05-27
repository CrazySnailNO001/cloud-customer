package com.xzh.customer.technical.spring.circularDependencies;

import com.google.errorprone.annotations.concurrent.LazyInit;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xzh
 * @date ：Created in 2020-05-04 14:34
 * @description：
 * @modified By：
 * @version:
 */
@Data
@Component
public class ClassA {
    public ClassA() {
//        this.classB = classB;
    }

//    private ClassB classB = new ClassB();


}
