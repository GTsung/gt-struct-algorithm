package com.gt.threads.design;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author GTsung
 * @date 2021/12/31
 */
public class WorkThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService esOne = Executors.newFixedThreadPool(2);
        ExecutorService esTwo = Executors.newFixedThreadPool(2);
        final CountDownLatch cdl = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {
            System.out.println("L1");
            esOne.execute(() -> {
                CountDownLatch cdl2 = new CountDownLatch(2);
                for (int j = 0; j < 2; j++) {
                    esTwo.execute(() -> {
                        System.out.println("L2");
                        cdl2.countDown();
                    });
                }
                try {
                    cdl2.await();
                    cdl.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        cdl.await();
        System.out.println("end");
        esTwo.shutdown();
        esOne.shutdown();
    }
}
