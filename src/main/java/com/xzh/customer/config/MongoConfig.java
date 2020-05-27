package com.xzh.customer.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * @author ：xzh
 * @date ：Created in 2020-05-11 16:24
 * @description：
 * @modified By：
 * @version:
 */
@Configuration
public class MongoConfig {
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory,
                                                       MongoMappingContext context, BeanFactory beanFactory) {

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, context);
        try {
            converter.setCustomConversions(beanFactory.getBean(MongoCustomConversions.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //don't save column _class to mongo collection
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }
}
