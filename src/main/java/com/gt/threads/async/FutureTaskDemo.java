package com.gt.threads.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author GTsung
 * @date 2021/12/26
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 建立FutureTask implements Runnable, Future
        // 此类实现了取消等方法，可在高并发条件下确保任务只执行一次
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        // 无返回值
        executorService.submit(futureTask);
        // 直接使用FutureTask获取结果
        System.out.println(futureTask.get());
    }


    static class Task implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(5000);
            return 9;
        }
    }
}
