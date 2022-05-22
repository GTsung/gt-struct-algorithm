package com.gt.threads.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author GTsung
 * @date 2022/5/22
 */
public class SemaphoreTwoDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    private static ReentrantLock lock = new ReentrantLock();

    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        SemaphoreTwoDemo semaphoreTwoDemo = new SemaphoreTwoDemo();
        System.out.println("======原来count=" + semaphoreTwoDemo.getCount());
        Thread.sleep(1000);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new SemaphoreThread(semaphore, semaphoreTwoDemo));
        }
        executorService.shutdown();
        Thread.sleep(1000);
        System.out.println("======现在count=" + semaphoreTwoDemo.getCount());
    }

    private static class SemaphoreThread extends Thread {

        private Semaphore semaphore;
        private SemaphoreTwoDemo semaphoreTwoDemo;

        public SemaphoreThread(Semaphore semaphore, SemaphoreTwoDemo semaphoreTwoDemo) {
            this.semaphore = semaphore;
            this.semaphoreTwoDemo = semaphoreTwoDemo;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                // 同时读取，因此需要加锁
                lock.lock();
                try {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + ",time=" + System.currentTimeMillis());
                    int count = semaphoreTwoDemo.getCount() + 1;
                    semaphoreTwoDemo.setCount(count);
                } finally {
                    lock.unlock();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
