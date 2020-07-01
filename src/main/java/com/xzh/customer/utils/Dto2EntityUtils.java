package com.xzh.customer.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-01 15:56
 * @description：
 * @modified By：
 * @version:
 */
@Slf4j
public class Dto2EntityUtils {
    /**
     * create by: xzh
     * description: Dto 转 Entity , Dto中为空的属性不填充
     */
    public static void populateIgnoreNullField(Object source, Object target) {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> targetClass = target.getClass();

        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(targetClass);

        for (PropertyDescriptor targetPd : propertyDescriptors) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }

                            Object value = readMethod.invoke(source);
                            if (null != value) {
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }

                                writeMethod.invoke(target, value);
                            }
                        } catch (Throwable var15) {
                            throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", var15);
                        }
                    }
                }
            }
        }
    }

    public static Object populate(Object src, Object target) {

        Method[] srcMethods = src.getClass().getMethods();
        Method[] targetMethods = target.getClass().getMethods();

        for (Method m : srcMethods) {
            String srcName = m.getName();
            if (srcName.startsWith("get")) {
                try {
                    Object result = m.invoke(src);
                    for (Method mm : targetMethods) {
                        String targetName = mm.getName();
                        if (targetName.startsWith("set")
                                && targetName.substring(3).equals(srcName.substring(3))
                                && mm.getParameterTypes()[0].isAssignableFrom(m.getReturnType())) {// 如果类型一致，或者m2的参数类型是m1的返回类型的父类或接口
                            mm.invoke(target, result);
                        }
                    }
                } catch (Exception e) {
                    log.info("转换失败");
                }
            }
        }
        return target;
    }


    public static void main(String[] args) {
        A a = new A();
        a.setName("张三");

        B b = new B();
        b.setAge(2);

        populateIgnoreNullField(a, b);
        System.out.println(a + "====" + b);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static
    class A {
        private String name;
        private Integer age;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static
    class B {
        private String name;
        private Integer age;
    }
}
