package com.xzh.customer.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ：xzh
 * @date ：Created in 2019-12-09 22:22
 * @description：
 * @modified By：
 * @version:
 */
@Component("springContextUtil")
public class SpringUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanId) {
        return (T) applicationContext.getBean(beanId);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> requiredType) {
        return (T) applicationContext.getBean(requiredType);
    }

    /**
     * Spring容器启动后，会把 applicationContext 给自动注入进来，然后我们把 applicationContext
     * 赋值到静态变量中，方便后续拿到容器对象
     *
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
