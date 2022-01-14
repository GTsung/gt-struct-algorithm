package com.gt.netty.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author GTsung
 * @date 2022/1/14
 */
@Slf4j
public class JDKFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Integer> future = es.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return 50;
            }
        });
        // 阻塞
        log.info("{}", future.get());
        es.shutdown();
    }
}
