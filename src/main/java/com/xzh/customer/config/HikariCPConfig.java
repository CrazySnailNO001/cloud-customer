package com.xzh.customer.config;

import com.xzh.customer.config.pop.threadPool.HikariProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-05 16:49
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Configuration
@EnableConfigurationProperties(HikariProperties.class)
public class HikariCPConfig {
    private final HikariProperties hikariProperties;

    public HikariCPConfig(HikariProperties hikariProperties) {
        this.hikariProperties = hikariProperties;
    }

    @Bean
    public DataSource defaultDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(hikariProperties.getUrl());
        config.setUsername(hikariProperties.getUserName());
        config.setPassword(hikariProperties.getPassword());
        config.setDriverClassName(hikariProperties.getDriverClassName());
        config.setPoolName(hikariProperties.getPoolName());
        config.setMaximumPoolSize(hikariProperties.getMaxPoolSize());
        config.setIdleTimeout(hikariProperties.getIdleTimeout());
        config.setMaxLifetime(hikariProperties.getMaxLifetime());
        config.setMinimumIdle(hikariProperties.getMinIdle());

        config.addDataSourceProperty("cachePrepStmts", "true"); //是否自定义配置，为true时下面两个参数才生效
        config.addDataSourceProperty("prepStmtCacheSize", "250"); //连接池大小默认25，官方推荐250-500
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048"); //单条语句最大长度默认256，官方推荐2048
        config.addDataSourceProperty("useServerPrepStmts", "true"); //新版本MySQL支持服务器端准备，开启能够得到显著性能提升
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("useLocalTransactionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        return new HikariDataSource(config);
    }
}
