package com.gt.threads.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author GTsung
 * @date 2021/12/29
 */
public class FutureTaskTwoDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft2 = new FutureTask<>(new Task2());

        FutureTask<String> ft1 = new FutureTask<>(new Task1(ft2));

        new Thread(ft1).start();
         new Thread(ft2).start();

        System.out.println(ft1.get());

    }

    static class Task1 implements Callable<String> {

        FutureTask<String> ft2;

        public Task1(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1开始洗水壶");
            Thread.sleep(100);
            // 阻塞获取第二个线程的结果
            String tea = ft2.get();
            System.out.println("T1泡茶");
            return tea + "茶";
        }
    }

    static class Task2 implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(100);
            System.out.println("找茶叶");
            return "普洱";
        }
    }
}
