package com.gt.threads.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author GTsung
 * @date 2021/12/29
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        // runAsync无返回值
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1洗水壶。。。");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T1烧开水");
            sleep(8, TimeUnit.SECONDS);
        });

        // supplyAsync有返回值
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2洗茶壶");
            sleep(2, TimeUnit.SECONDS);
            System.out.println("T2洗茶杯");
            sleep(2, TimeUnit.SECONDS);
            System.out.println("T2拿茶叶");
            return "普洱";
        });

        // thenApply thenAccept thenRun thenCombine表示串行关系
        // thenCombine表示与
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T1拿到茶叶" + tf);
            return "上茶：" + tf;
        });
        // applyToEither表示或

        System.out.println(f3.join());

        CompletableFuture<Integer> f0 = CompletableFuture.supplyAsync(() -> (7/0))
                .thenApply(r->r*10)
                .exceptionally(e->0); // 捕获异常
        System.out.println(f0.join());
    }

    private static void sleep(int num, TimeUnit u) {
        try {
            u.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
