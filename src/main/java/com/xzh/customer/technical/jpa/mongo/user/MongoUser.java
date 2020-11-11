package com.xzh.customer.technical.jpa.mongo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author ：xzh
 * @date ：Created in 2020-05-08 17:01
 * @description：
 * @modified By：
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
class MongoUser implements Serializable {

    private static final long serialVersionUID = -3896901585340927241L;

    @Id
    private Long id;
    private String name;
    private String password;
    private int age;
}
