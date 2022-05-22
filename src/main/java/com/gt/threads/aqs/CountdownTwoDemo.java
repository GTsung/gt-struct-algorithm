package com.gt.threads.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author GTsung
 * @date 2022/5/22
 */
public class CountdownTwoDemo {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static ReentrantLock lock = new ReentrantLock();

    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static void main(String[] args) throws InterruptedException {

        CountdownTwoDemo countdownTwoDemo = new CountdownTwoDemo();
        System.out.println("原来的count=" + countdownTwoDemo.getCount());
        Thread.sleep(1000);

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new CountThread(countDownLatch, countdownTwoDemo));
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("修改后的count=" + countdownTwoDemo.getCount());

    }

    private static class CountThread implements Runnable {

        private CountDownLatch countDownLatch;
        private CountdownTwoDemo countdownTwoDemo;

        public CountThread(CountDownLatch countDownLatch, CountdownTwoDemo countdownTwoDemo) {
            this.countDownLatch = countDownLatch;
            this.countdownTwoDemo = countdownTwoDemo;
        }

        @Override
        public void run() {
            try {


                // 对公共资源的修改仍然需要加锁
                lock.lock();
                Thread.sleep(100);
                long currentTime = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + ":currentTime=" + currentTime);
                int count = countdownTwoDemo.getCount() + 1;
                countdownTwoDemo.setCount(count);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                countDownLatch.countDown();

            }

        }
    }
}
