package com.xzh.customer.config.pop.redisson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：xzh
 * @date ：Created in 2020-12-28 15:31
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@ConfigurationProperties(prefix = "sys.data.redisson")
@Data
public class RedissonClientConfig {
    private String address;
    private String module;
}
