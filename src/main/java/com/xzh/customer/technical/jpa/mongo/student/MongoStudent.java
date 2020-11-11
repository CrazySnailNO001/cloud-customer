package com.xzh.customer.technical.jpa.mongo.student;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import com.xzh.customer.helper.DefaultInstantDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

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
@Document("student")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MongoStudent implements Serializable {

    private static final long serialVersionUID = -3896901585340927241L;

    @Id
    private Long id;
    private String name;
    private Integer age;
    private String className;
    private Integer isDelete;

    private List<Subject> subjects;

    @JsonSerialize(using = InstantSerializer.class)
    @JsonDeserialize(using = DefaultInstantDeserializer.class)
    private Instant createTime;

    @JsonSerialize(using = InstantSerializer.class)
    @JsonDeserialize(using = DefaultInstantDeserializer.class)
    private Instant updateTime;
}
