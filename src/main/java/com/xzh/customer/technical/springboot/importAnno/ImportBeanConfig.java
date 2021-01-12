package com.xzh.customer.technical.springboot.importAnno;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-20 17:11
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Configuration
@Import({ImportBeanDemo.class, UserServiceBeanDefinitionRegistrar.class})
public class ImportBeanConfig {
}
