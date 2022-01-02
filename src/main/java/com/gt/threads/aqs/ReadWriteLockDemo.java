package com.gt.threads.aqs;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author GTsung
 * @date 2021/12/28
 */
public class ReadWriteLockDemo {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();

    private int value;

    /**
     * 写锁为独占锁:
     *          获取锁时首先需要看state是否为0，为0则设置独占线程； 不为0查看写线程数量及写线程是否为当前线程；
     *          寫綫程為0表示現在讀鎖直接進入隊列阻塞，如果当前线程不是該綫程查看是否需要阻塞，是當前綫程則最后设置独占线程
     *          释放锁时查看当前独占线程是否为空，为空表示当前不是写锁加锁，之后减少state并判断state=0否，为0则释放成功
     * 读锁为共享锁：
     *          获取锁时查看当前是否写线程为0而且当前线程是否为独占线程，否则失败；读锁线程是否为0，为0则设置第一个线程，之后将读线程锁数量计数器自增
     *          释放锁时查看第一个读线程是否为当前线程，是则置空，减少当前线程的计数
     * @param lock
     * @return
     * @throws InterruptedException
     */

    public Object readValue(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            System.out.println("read--------------");
            Thread.sleep(100);
            System.out.println(" read done");
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void writeValue(Lock lock, int val) throws InterruptedException {
        try {
            lock.lock();
            System.out.println("write----------");
            Thread.sleep(100);
            value = val;
            System.out.println("write done ");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        Thread t1 = new Thread(() -> {
            try {
                readWriteLockDemo.readValue(readLock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                readWriteLockDemo.writeValue(writeLock, new Random().nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ExecutorService read = Executors.newFixedThreadPool(18);
        ExecutorService write = Executors.newFixedThreadPool(18);
        for (int i = 0; i < 18; i++) {
            read.execute(t1);
            write.execute(t2);
        }





    }
}
