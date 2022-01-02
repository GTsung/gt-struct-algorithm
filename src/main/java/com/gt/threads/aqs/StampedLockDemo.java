package com.gt.threads.aqs;

import java.util.concurrent.locks.StampedLock;

/**
 * @author GTsung
 * @date 2021/12/29
 */
public class StampedLockDemo {

    /**
     * ReadWriteLock 支持两种模式：一种是读锁，一种是写锁。而 StampedLock 支持三种模式，
     * 分别是：写锁、悲观读锁和乐观读。其中，写锁、悲观读锁的语义和 ReadWriteLock 的写锁、读锁的语义非常类似，
     * 允许多个线程同时获取悲观读锁，但是只允许一个线程获取写锁，写锁和悲观读锁是互斥的。
     * 不同的是：StampedLock 里的写锁和悲观读锁加锁成功之后，都会返回一个 stamp；然后解锁的时候，需要传入这个 stamp
     *
     * 不可重入readLock与writeLock不可响应中断否则CPU飙升，可使用readLockInterruptibly
     *
     * @param args
     */
    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();
    }

    static class Task implements Runnable {
        private StampedLock stampedLock;

        public Task(StampedLock stampedLock) {
            this.stampedLock = stampedLock;
        }

        @Override
        public void run() {
            long stamp = stampedLock.readLock();
            try {
                // do sth
            } finally {
                stampedLock.unlockRead(stamp);
            }

            long stamp2 = stampedLock.writeLock();
            try {
                //
            } finally {
                stampedLock.unlockWrite(stamp2);
            }
        }
    }
}
