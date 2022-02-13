package com.gt.threads.aqs;

import java.util.concurrent.locks.StampedLock;

/**
 * @author GTsung
 * @date 2022/2/13
 */
public class StampLockDemoTwo {

    public static void main(String[] args) {

    }

    static class Point {
        private double x, y;
        private final StampedLock sl = new StampedLock();

        // 多個綫程調用此函數修改x與y的值
        void move(double deltaX, double deltaY) {
            long stamp = sl.writeLock();
            try {
                x = deltaX;
                y = deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        // 多個綫程調用此函數求距離
        double distanceFromOrigin() {
            // 先使用樂觀讀
            long stamp = sl.tryOptimisticRead();
            double currentX = x, currentY = y;
            // 讀的期間有其他綫程修改數據，讀的是髒數據，放棄
            if (!sl.validate(stamp)) {
                // 升級為悲觀讀
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
