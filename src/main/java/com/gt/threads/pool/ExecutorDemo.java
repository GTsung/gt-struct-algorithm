package com.gt.threads.pool;

import java.util.concurrent.*;

/**
 * @author GTsung
 * @date 2021/12/30
 */
public class ExecutorDemo {

    private static final CountDownLatch countDown = new CountDownLatch(10);

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = new ThreadPoolExecutor(2, 3, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Task());
        }
        countDown.await();
        threadPool.shutdown();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "===========");
            countDown.countDown();
        }
    }
}
