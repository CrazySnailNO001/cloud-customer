package com.xzh.customer.utils;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：xzh
 * @date ：Created in 2019-10-15 10:21
 * @description： JavaBean转Map
 * @modified By：
 * @version:
 */
@Slf4j
public class BeanUtils {

    public static Map<String, Object> bean2map(Object bean) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //获取JavaBean的描述器
        BeanInfo b = Introspector.getBeanInfo(bean.getClass(), Object.class);
        //获取属性描述器
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        //对属性迭代
        for (PropertyDescriptor pd : pds) {
            //属性名称
            String propertyName = pd.getName();
            //属性值,用getter方法获取
            Method m = pd.getReadMethod();
            Object properValue = m.invoke(bean);//用对象执行getter方法获得属性值

            //把属性名-属性值 存到Map中
            map.put(propertyName, properValue);
        }
        return map;
    }

    public static <T> T map2bean(Map<String, Object> map, Class<T> clz) throws Exception {
        //创建一个需要转换为的类型的对象
        T obj = clz.newInstance();
        //从Map中获取和属性名称一样的值，把值设置给对象(setter方法)

        //得到属性的描述器
        BeanInfo b = Introspector.getBeanInfo(clz, Object.class);
        PropertyDescriptor[] pds = b.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            //得到属性的setter方法
            Method setter = pd.getWriteMethod();
            //得到key名字和属性名字相同的value设置给属性
            setter.invoke(obj, map.get(pd.getName()));
        }
        return obj;
    }

    public static <T> T mapToJavaBean(Map<String, Object> map, Class<T> clazz) throws Exception {
        // new 出一个对象
        T obj = clazz.newInstance();
        // 获取javaBean的BeanInfo对象
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
        // 获取属性描述器
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            // 获取属性名
            String key = propertyDescriptor.getName();
            Object value = map.get(key);
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (map.containsKey(key)) {
                // 解决 argument type mismatch 的问题，转换成对应的javaBean属性类型
                String typeName = propertyDescriptor.getPropertyType().getTypeName();
                // System.out.println(key +"<==>"+ typeName);
                if ("java.lang.Integer".equals(typeName)) {
                    value = Integer.parseInt(value.toString());
                }
                if ("java.lang.Long".equals(typeName)) {
                    value = Long.parseLong(value.toString());
                }
                if ("java.util.Date".equals(typeName)) {
                    value = new SimpleDateFormat("yyyy-MM-dd").parse(value.toString());
                }
            }
            // 通过反射来调用javaBean定义的setName()方法
            writeMethod.invoke(obj, value);
        }
        return obj;
    }
}
