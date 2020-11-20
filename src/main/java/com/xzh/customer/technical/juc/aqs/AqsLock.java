package com.xzh.customer.technical.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author ：xzh
 * @date ：Created in 2020-07-05 11:52
 * @description：
 * @modified By：
 * @version:
 */
public class AqsLock implements Lock {
    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public void lockInterruptibly() {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    static class Sync extends AbstractQueuedSynchronizer {
        Sync() {
            super();
        }

        @Override
        protected boolean tryAcquire(int arg) { //acquire方法调用
            assert arg == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());//互斥锁,共享锁
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) { //release方法调用
            assert arg == 1;
            //必须在当前线程持有锁的情况下才能释放锁
            if (!isHeldExclusively()) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }
    }
}
