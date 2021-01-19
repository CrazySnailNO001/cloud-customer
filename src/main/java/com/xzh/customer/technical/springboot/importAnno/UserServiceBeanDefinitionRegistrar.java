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
    /**
     * create by: xzh
     * description:
     * create time: 2021-01-12 17:16
     * @Param: annotationMetadata:通过这个参数可以拿到类的元数据信息
     * @Param: beanDefinitionRegistry:通过这个参数可以操作IOC容器
     * @return void
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(ImportBeanRegistrar.class);
        beanDefinitionRegistry.registerBeanDefinition("importBeanRegistrar", builder.getBeanDefinition());
    }
}
