package com.xzh.customer.technical.Transactional;

/**
 * @author XZHH
 * @Description: 隔离级别数字配置含义:
 * -1：数据库默认隔离级别
 *  1：未提交读
 *  2：读写提交
 *  4：可重复读
 *  8：串行化
 * @modify By:
 **/
public enum Isolation {
    DEFAULT(-1),
    READ_UNCOMMITTED(1),
    READ_COMMITTED(2),
    REPEATABLE_READ(4),
    SERIALIZABLE(8);

    private final int value;

    private Isolation(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}