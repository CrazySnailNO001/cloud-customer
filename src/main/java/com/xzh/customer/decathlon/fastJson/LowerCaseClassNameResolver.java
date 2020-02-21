package com.xzh.customer.decathlon.fastJson;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-23 23:36
 * @description：
 * @modified By：
 * @version:
 */
public class LowerCaseClassNameResolver extends TypeIdResolverBase {
    public LowerCaseClassNameResolver() {
    }

    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return this.idFromValue(value);
    }

    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
