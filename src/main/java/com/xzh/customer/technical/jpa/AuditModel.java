package com.xzh.customer.technical.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createTime", "updateTime"},
        allowGetters = true
)
@Data
public abstract class AuditModel implements Serializable {

    private static final long serialVersionUID = -1857057871036541939L;

    @Column(name = "create_time", nullable = false, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @CreatedDate
    private Instant createTime;

    @Column(name = "update_time", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @LastModifiedDate
    private Instant updateTime;
}
