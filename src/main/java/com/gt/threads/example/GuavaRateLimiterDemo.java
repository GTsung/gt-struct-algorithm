package com.gt.threads.example;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author GTsung
 * @date 2021/12/31
 */
public class GuavaRateLimiterDemo {

    /**
     * 限流器
     *
     * @param args
     */
    public static void main(String[] args) {
        limit();
    }

    private static void limit() {
        RateLimiter rateLimiter = RateLimiter.create(2.0);
        ExecutorService es = Executors.newFixedThreadPool(1);
        AtomicLong prev = new AtomicLong(System.nanoTime());
        for (int i = 0; i < 20; i++) {
            // 限流
            rateLimiter.acquire();
            es.execute(() -> {
                long cur = System.nanoTime();
                System.out.println((cur - prev.get()) / 1000_000);
                prev.set(cur);
            });
        }
    }


}
