package com.gt.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionHandlerTest {

    public static void main(String[] args) {
//        extracted();

        pool();
    }

    private static void pool() {
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        executorService.execute(new MyThread());
        executorService.shutdown();
    }

    private static void extracted() {
        Thread t = new Thread(new MyThread());
        t.setUncaughtExceptionHandler(new MyExceptionHandler());
        t.start();
    }

    private static class MyThread implements Runnable {
        @Override
        public void run() {
            // 使用线程池的execute方法才能捕获，而且这个异常设置要在线程内部进行
            Thread.currentThread().setUncaughtExceptionHandler(new MyExceptionHandler());
            System.out.println(12/12);
            System.out.println(12/0);
        }
    }

    /**
     * 这个异常处理器用于捕获线程出现的异常，因为主线程无法捕获，也可以在线程内部try catch
     */
    private static class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + ",e=" + e.getMessage());
        }
    }
}
