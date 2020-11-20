package com.xzh.customer.technical.juc.async;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-16 17:57
 * @description：解决 @Async 注解由于@EnableAspectJAutoProxy(exposeProxy = true) 不起作用而失效的问题
 * @modified By：
 * @version:
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition(TaskManagementConfigUtils.ASYNC_ANNOTATION_PROCESSOR_BEAN_NAME);
//        beanDefinition.getPropertyValues().add("exposeProxy", true);
    }
}
