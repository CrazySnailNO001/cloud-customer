package com.xzh.customer.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-09 11:02
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class SqlUtils {
    public static final int SORT_DIRECTION_ASC = 1;
    public static final int SORT_DIRECTION_DESC = 2;

    public static List<Sort.Order> string2Sorts(String target, int direction) {
        if (StringUtils.isEmpty(target)) {
            return null;
        }

        List<Sort.Order> orderList = new ArrayList<>();
        String[] sorts = StringUtils.split(target, ',');

        if (SORT_DIRECTION_ASC == direction) {
            Arrays.stream(sorts).forEach(x -> {
                orderList.add(new Sort.Order(Sort.Direction.ASC, x));
            });
        } else {
            Arrays.stream(sorts).forEach(x -> {
                orderList.add(new Sort.Order(Sort.Direction.DESC, x));
            });
        }

        return orderList;
    }
}
