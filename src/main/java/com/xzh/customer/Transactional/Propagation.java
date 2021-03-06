package com.xzh.customer.Transactional;

/**
 * @author XZHH
 * @Description: 事务传播行为:
    1 REQUIRED(0)
    需要事务，它是默认传播行为，如果当前存在事务，就沿用当前事务，否则新建一个事务运行子方法。

    2 SUPPORTS(1)
    支持事务，如果当前存在事务，就沿用当前事务，如果不存在，则继续采用无事务的方式运行子方法。

    3 MANDATORY(2)
    必须使用事务，如果当前没有事务，则会抛出异常，如果存在当前事务，就沿用当前事务。

    4 REQUIRES_NEW(3)
    无论当前事务是否存在，都会创建新事务运行方法，这样新事务就可以拥有新的锁和隔离级别等特性，与当前事务相互独立。

    5 NOT_SUPPORTED(4)
    不支持事务，当前存在事务时，将挂起事务，运行方法。

    6 NEVER(5)
    不支持事务，如果当前方法存在事务，则抛出异常，否则继续使用无事务机制运行。

    7 NESTED(6)
    在当前方法调用子方法时，如果子方法发生异常，只回滚子方法执行过的SQL，而不回滚当前方法的事务。

    常用的传播行为主要有三种：REQUIRED 、REQUIRES_NEW、 NESTED。
 * @create 2019/5/7 0007 16:45
 * @modify By:
 **/
public enum Propagation {
    REQUIRED(0),
    SUPPORTS(1),
    MANDATORY(2),
    REQUIRES_NEW(3),
    NOT_SUPPORTED(4),
    NEVER(5),
    NESTED(6);

    private final int value;

    private Propagation(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
