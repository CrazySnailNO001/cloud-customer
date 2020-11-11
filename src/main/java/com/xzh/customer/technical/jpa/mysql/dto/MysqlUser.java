package com.xzh.customer.technical.jpa.mysql.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-09 16:47
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Data
@Entity(name = "MysqlUser")
@Table(name = "mysql_user")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MysqlUser implements Serializable {
    private static final long serialVersionUID = 2897562220313951500L;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "class_name")
    private String className;

    @Column(name = "score")
    private Integer score;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "update_time")
    private String updateTime;
}
