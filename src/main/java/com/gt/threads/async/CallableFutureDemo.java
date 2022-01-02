package com.gt.threads.async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author GTsung
 * @date 2021/12/26
 */
public class CallableFutureDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Task task = new Task();
        Future<Integer> future = executorService.submit(task);

        // 试图取消，参数为是否采用中断的方式取消线程执行
//        future.cancel(false);
        // 会阻塞获取
        System.out.println(future.get());

    }

    // callable接口可以使用线程池执行并可以通过Future获取到结果
    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(5000);
            return 2;
        }
    }
}
