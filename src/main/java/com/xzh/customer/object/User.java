package com.xzh.customer.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-31 10:42
 * @description：
 * @modified By：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private int age;

    @Override
    public boolean equals(Object o) {
        return false;
    }

//    @Override
//    public boolean equals(Object o) {
//        return true;
//    }

    @Override
    public int hashCode() {
        return 1;
    }
}
