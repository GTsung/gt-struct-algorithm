package com.gt.threads.aqs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author GTsung
 * @date 2021/12/27
 */
public class LockDemo {

//    private static final Lock lock = new ReentrantLock();
    private static final Lock lock = new ReentrantLock(true);

    private static final Condition condition = lock.newCondition();


    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Task(), "task-01");
        Thread t2 = new Thread(new Task(), "task-02");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("----------");
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            lock.lock();

            try {
                // 两个线程，这里用if
                if (!"task-02".equals(Thread.currentThread().getName())) {
                    condition.await();
                }
                System.out.println("++++++" + Thread.currentThread().getName());
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
