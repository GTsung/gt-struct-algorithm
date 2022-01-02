package com.gt.threads.async;

import java.util.concurrent.*;

/**
 * @author GTsung
 * @date 2021/12/29
 */
public class CompletionServiceDemo {

    public static void main(String[] args) throws Exception {
//        method01();

        // 批量執行
        useCompletionService();
    }

    /**
     * 使用CompletionService优化
     * ExecutorCompletionService构造方法：需要一个线程池和阻塞队列(不指定则默认为LinkedBlockingQueue)
     * 这个阻塞队列存放future对象
     * @throws Exception
     */
    private static void useCompletionService() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(3);
        // 创建CompletionService
        CompletionService cs = new ExecutorCompletionService(es);
        cs.submit(CompletionServiceDemo::getPrice1);
        cs.submit(CompletionServiceDemo::getPrice2);
        cs.submit(CompletionServiceDemo::getPrice3);
        for (int i = 0; i < 3; i++) {
            // 如果getPrice1时间长于其他，则会先返回时间段的结果
            System.out.println(cs.take().get());
        }
    }

    /**
     * 阻塞获取结果保存数据，如果f2获取结果所需时间比较短，也得必须等待f1获取完毕
     * @throws Exception
     */
    private static void method01() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(3);
        // 异步询价
        Future<Integer> f1 = es.submit(CompletionServiceDemo::getPrice1);
        Future<Integer> f2 = es.submit(CompletionServiceDemo::getPrice2);
        Future<Integer> f3 = es.submit(CompletionServiceDemo::getPrice3);
        long start = System.currentTimeMillis();
        // 保存价格
        int r1 = f1.get(); // 此处会阻塞
        es.execute(() -> save(r1));
        int r2 = f2.get(); // 阻塞
        es.execute(() -> save(r2));
        int r3 = f3.get(); // 阻塞
        es.execute(() -> save(r3));
        long end = System.currentTimeMillis();
        System.out.println("获取结果完毕，最少需要" + (end-start)/1000 + "s");
    }

    private static int getPrice1() {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private static int getPrice2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }

    private static int getPrice3() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 3;
    }

    private static void save(int price) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
