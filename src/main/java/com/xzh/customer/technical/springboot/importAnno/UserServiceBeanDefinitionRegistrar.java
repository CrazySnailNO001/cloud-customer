package com.xzh.customer.technical.springboot.importAnno;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-20 17:16
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class UserServiceBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinitionBuilder userService = BeanDefinitionBuilder.rootBeanDefinition(ImportBeanRegistrar.class);
        beanDefinitionRegistry.registerBeanDefinition("importBeanRegistrar", userService.getBeanDefinition());
    }
}
