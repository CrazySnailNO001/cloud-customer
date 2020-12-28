package com.xzh.customer.config;

import com.xzh.customer.config.pop.redisson.RedissonClientConfig;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author ：xzh
 * @date ：Created in 2020-12-28 10:46
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Configuration
@EnableConfigurationProperties({RedissonClientConfig.class})
public class RedisSonConfig {
    @Resource
    private final RedissonClientConfig clientConfig;

    public RedisSonConfig(RedissonClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    /**
     * description: yml文件形式加载,详细配置见:redisson.yml
     */
    @Bean
    public RedissonClient ymlRedisSonClient() throws IOException {
        return Redisson.create(
                Config.fromYAML(new ClassPathResource("redisson.yml").getInputStream()));
    }

    /**
     * description: 单机模式
     */
    @Bean
    @Primary
    @ConditionalOnExpression("'${sys.data.redisson.module}'.equals('single')")
    public RedissonClient singleRedisSonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(clientConfig.getAddress());
        return Redisson.create(config);
    }

    /**
     * description: 哨兵模式
     */
    @Bean
    @ConditionalOnExpression("'${sys.data.redisson.module}'.equals('sentinel')")
    public RedissonClient sentinelRedisSonClient() {
        Config config = new Config();
        config.useSentinelServers()
                .setMasterName("mymaster")
                .addSentinelAddress("127.0.0.1:26389", "127.0.0.1:26379")
                .addSentinelAddress("127.0.0.1:26319");
        return Redisson.create(config);
    }

    /**
     * description: 主从模式
     */
    @Bean
    @ConditionalOnExpression("'${sys.data.redisson.module}'.equals('masterSlave')")
    public RedissonClient masterSlaveRedisSonClient() {
        Config config = new Config();
        config.useMasterSlaveServers()
                .setMasterAddress("127.0.0.1:6379")
                .addSlaveAddress("127.0.0.1:6389", "127.0.0.1:6332", "127.0.0.1:6419")
                .addSlaveAddress("127.0.0.1:6399");
        return Redisson.create(config);
    }

    /**
     * description: 集群模式
     */
    @Bean
//    @ConditionalOnProperty("sys.data.redisson.cluster")
    @ConditionalOnExpression("'${sys.data.redisson.module}'.equals('cluster')")
    public RedissonClient clusterRedisSonClient() {
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000) // cluster state scan interval in milliseconds
                .addNodeAddress("127.0.0.1:7000", "127.0.0.1:7001")
                .addNodeAddress("127.0.0.1:7002");
        return Redisson.create(config);
    }
}
