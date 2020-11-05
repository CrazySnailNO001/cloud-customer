package com.xzh.customer.config.pop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-05 16:51
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@ConfigurationProperties(prefix = "biz.data.hikari.datasource")
@Data
public class HikariProperties {
    private String url;
    private String userName;
    private String password;
    private String driverClassName;
    private String poolName;
    private Integer maxPoolSize;
    private Long idleTimeout;
    private Long maxLifetime;
    private Integer minIdle;
}
