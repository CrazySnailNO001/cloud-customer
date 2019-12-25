package com.xzh.customer.decathlon.strategy.constants;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum CarrierEnum {
    DEFAULT(0, "default", "0", "0"),
    SF_QIAO(1, "sfexpress qiao", "20", "0"),
    ELEME(2, "eleme", "2", "0"),
    SF_SAMECITY(3, "sfsamecity", "2", "0"),
    SAMESTORE(4, "samestore", "2", "0"),
    TMALL_DSS(5, "tmalldss", "2", "0");

    private int key;
    private String name;
    private String leadTimeHour;
    private String supplyPrice;

    private CarrierEnum() {
    }

    CarrierEnum(int key, String name, String leadTimeHour, String supplyPrice) {

        this.key = key;
        this.name = name;
        this.leadTimeHour = leadTimeHour;
        this.supplyPrice = supplyPrice;
    }

    public static List toList() {
        List<CarrierEnum> list = Lists.newArrayList();
        list.addAll(Arrays.asList(CarrierEnum.values()));
        return list;
    }

    public static List nameList() {
        List<String> list = Lists.newArrayList();
        for (CarrierEnum carrierEnum : CarrierEnum.values()) {
            list.add(carrierEnum.name);
        }
        return list;
    }

    public static CarrierEnum getCarrierEnum(String name) {
        CarrierEnum carrierEnum = null;
        for (CarrierEnum carrierNameEnum : CarrierEnum.values()) {
            if (carrierNameEnum.getName().equals(name)) {
                carrierEnum = carrierNameEnum;
            }
        }
        return carrierEnum;
    }
}
