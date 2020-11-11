package com.xzh.customer.utils;

import java.util.Map;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-11 16:15
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class DefaultUtils {

    public static <T> T valuationForNull(T sourceValue, T defaultValue) {
        if (sourceValue == null)
            sourceValue = defaultValue;
        return sourceValue;
    }

    public static void putIfNotNull(Map<String, Object> map, String key, Object value) {
        if (null != value)
            map.put(key, value);
    }
}
