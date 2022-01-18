package com.gt.threads;

import java.util.concurrent.locks.StampedLock;

/**
 * @author GTsung
 * @date 2022/1/18
 */
public class StampedLockDemo {

    /**
     * 讀與讀不互斥，讀與寫不互斥，寫與寫互斥
     */
    static class Point {
        private double x, y;
        private final StampedLock sl = new StampedLock();

        void move(double deltaX, double deltaY) {
            long stamp = sl.writeLock();
            try {
                x = deltaX;
                y = deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        double distanceFromOrigin() {
            // 多個讀綫程讀數據，此處使用樂觀讀
            long stamp = sl.tryOptimisticRead();

            double currentX = x, currentY = y;
            // 讀的期間如果有其他綫程修改數據，則讀出來的是髒數據，此時升級爲悲觀讀
            if (!sl.validate(stamp)) {
                stamp = sl.readLock();
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }
    }
}
