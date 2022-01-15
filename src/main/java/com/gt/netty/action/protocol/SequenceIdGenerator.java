package com.gt.netty.action.protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author GTsung
 * @date 2022/1/15
 */
public abstract class SequenceIdGenerator {

    private static final AtomicInteger id = new AtomicInteger();

    public static int nextId() {
        return id.incrementAndGet();
    }

}
