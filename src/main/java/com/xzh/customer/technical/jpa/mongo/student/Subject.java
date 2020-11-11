package com.xzh.customer.technical.jpa.mongo.student;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-06 17:35
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
class Subject implements Serializable {

    private static final long serialVersionUID = -4809389400053553457L;

    private String subjectName;
    private String teacherName;
    private Integer score;
}
