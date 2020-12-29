package com.xzh.customer.technical.juc.providerAndConsumer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-25 17:29
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@Slf4j
public class ProducerConsumerDemo {
    private static final int MAX_SIZE = 10;


    public static void main(String[] args) {
//        PCWNStorage storage = new PCWNStorage();
//        PCASStorage storage = new PCASStorage();
//        LinkedBlockingQueueStorage storage = new LinkedBlockingQueueStorage();
        SemaphoreStorage storage = new SemaphoreStorage();


        IntStream.range(0, 1).forEach(value -> {
            Thread thread = new Thread(new PCWNProducer(storage));
            thread.setName("生产线程" + value);
            thread.start();
        });
        IntStream.range(0, 1).forEach(value -> {
            Thread thread = new Thread(new SleepConsumer(storage));
            thread.setName("消费线程" + value);
            thread.start();
        });


    }

    static class PCWNProducer implements Runnable {
        private PCWNStorage pcwnStorage;
        private ConditionStorage pcasStorage;
        private LinkedBlockingQueueStorage pclbqStorage;
        private SemaphoreStorage semaphoreStorage;

        PCWNProducer(PCWNStorage storage) {
            this.pcwnStorage = storage;
        }

        PCWNProducer(ConditionStorage pcasStorage) {
            this.pcasStorage = pcasStorage;
        }

        PCWNProducer(LinkedBlockingQueueStorage pclbqStorage) {
            this.pclbqStorage = pclbqStorage;
        }

        public PCWNProducer(SemaphoreStorage semaphoreStorage) {
            this.semaphoreStorage = semaphoreStorage;
        }

        @SuppressWarnings("InfiniteLoopStatement")
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                Thread.sleep(1000);
//                pcwnStorage.produce();
//                pcasStorage.produce();
//                pclbqStorage.produce();
                semaphoreStorage.produce();
            }
        }
    }

    static class SleepConsumer implements Runnable {
        private PCWNStorage storage;
        private ConditionStorage pcasStorage;
        private LinkedBlockingQueueStorage pclbqStorage;
        private SemaphoreStorage semaphoreStorage;

        SleepConsumer(PCWNStorage storage) {
            this.storage = storage;
        }

        public SleepConsumer(ConditionStorage pcasStorage) {
            this.pcasStorage = pcasStorage;
        }

        public SleepConsumer(LinkedBlockingQueueStorage pclbqStorage) {
            this.pclbqStorage = pclbqStorage;
        }

        public SleepConsumer(SemaphoreStorage semaphoreStorage) {
            this.semaphoreStorage = semaphoreStorage;
        }

        @SuppressWarnings("InfiniteLoopStatement")
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                Thread.sleep(2000);
//                storage.consumer();
//                pcasStorage.consumer();
//                pclbqStorage.consumer();
                semaphoreStorage.consumer();
            }
        }
    }

    /*
    Object的wait & notifyAll
     */
    public static class PCWNStorage {
        private final LinkedList<String> list = new LinkedList<>();
        private int size = 0;

        void produce() {
            synchronized (list) {
                while (size + 1 > MAX_SIZE) {
                    log.info("[ {} ] 仓库已满", Thread.currentThread().getName());
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(Thread.currentThread().getName());
                size++;
                log.info("[ {} ] 生产了一个产品,实时库存为: {}", Thread.currentThread().getName(), size);
                list.notifyAll();
            }
        }

        void consumer() {
            synchronized (list) {
                while (size == 0) {
                    log.info("[ {} ] 仓库为空", Thread.currentThread().getName());
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.remove();
                size--;
                log.info("[ {} ] 消费了一个产品,实时库存为: {}", Thread.currentThread().getName(), size);
                list.notifyAll();
            }
        }
    }

    /*
    lock的await & signalAll
     */
    public static class ConditionStorage {
        private final LinkedList<String> list = new LinkedList<>();
        private final Lock lock = new ReentrantLock();
        private final Condition full = lock.newCondition();
        private final Condition empty = lock.newCondition();
        private AtomicInteger size = new AtomicInteger(0);

        void produce() throws InterruptedException {
            lock.lockInterruptibly();
            try {
                while (size.get() + 1 > MAX_SIZE) {
                    log.info("[ {} ] 仓库已满", Thread.currentThread().getName());
                    try {
                        full.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(Thread.currentThread().getName());
                size.getAndIncrement();
                log.info("[ {} ] 生产了一个产品,实时库存为: {}", Thread.currentThread().getName(), size);
                empty.signalAll();
            } finally {
                lock.unlock();
            }
        }

        void consumer() throws InterruptedException {
            lock.lockInterruptibly();
            try {
                while (size.get() == 0) {
                    log.info("[ {} ] 仓库为空", Thread.currentThread().getName());
                    try {
                        empty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.remove();
                size.getAndDecrement();
                log.info("[ {} ] 消费了一个产品,实时库存为: {}", Thread.currentThread().getName(), size);
                full.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    /*
    阻塞队列
     */
    public static class LinkedBlockingQueueStorage {

        // 仓库存储的载体
        private LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(10);
        void produce() {
            try{
                queue.put(new Object());
                System.out.println("【生产者" + Thread.currentThread().getName()
                        + "】生产一个产品，现库存" + queue.size());
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        void consumer() {
            try{
                queue.take();
                System.out.println("【消费者" + Thread.currentThread().getName()
                        + "】消费了一个产品，现库存" + queue.size());
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    /*
     * 信号量
     */
    public static class SemaphoreStorage {

        // 仓库的最大容量
        final Semaphore notFull = new Semaphore(10);
        // 将线程挂起，等待其他来触发
        final Semaphore notEmpty = new Semaphore(0);
        // 互斥锁
        final Semaphore mutex = new Semaphore(1);
        // 仓库存储的载体
        private LinkedList<Object> list = new LinkedList<>();

        void produce()
        {
            try {
                notFull.acquire();
                mutex.acquire();
                list.add(new Object());
                System.out.println("【生产者" + Thread.currentThread().getName()
                        + "】生产一个产品，现库存" + list.size());
            }
            catch (Exception e) {
                e.printStackTrace();
            } finally {
                mutex.release();
                notEmpty.release();
            }
        }

        void consumer()
        {
            try {
                notEmpty.acquire();
                mutex.acquire();
                list.remove();
                System.out.println("【消费者" + Thread.currentThread().getName()
                        + "】消费一个产品，现库存" + list.size());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mutex.release();
                notFull.release();
            }
        }
    }
}
