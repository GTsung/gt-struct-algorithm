package com.gt.netty.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author GTsung
 * @date 2022/1/14
 */
@Slf4j
public class NettyPromiseTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoop eventExecutors = new NioEventLoopGroup().next();
        // 可以主動創建promise
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                promise.setFailure(e);
            }
            // 計算完畢后向promise塞入結果，也可以填入異常
            promise.setSuccess(100);
        }).start();
        log.info("{}", promise.get());
        System.out.println(promise.get());
    }
}
