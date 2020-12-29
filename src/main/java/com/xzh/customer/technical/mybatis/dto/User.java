package com.xzh.customer.technical.mybatis.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User {
    private Integer id;

    private String name;

    private Integer age;

    private String className;

    private Integer score;
}