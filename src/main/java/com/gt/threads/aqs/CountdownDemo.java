package com.gt.threads.aqs;

import java.util.concurrent.CountDownLatch;

/**
 * @author GTsung
 * @date 2021/12/29
 */
public class CountdownDemo {

    /**
     * countDownLatch首先从构造方法中指定state，调用await时查看state是否为0，部位0阻塞加入阻塞队列
     * countdown --> 将state值减一，如果state减至0，则唤醒阻塞线程
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(new Task(countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println("end");
    }


    static class Task implements Runnable {
        private CountDownLatch countDownLatch;

        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hiahia");
            countDownLatch.countDown();
        }
    }
}
