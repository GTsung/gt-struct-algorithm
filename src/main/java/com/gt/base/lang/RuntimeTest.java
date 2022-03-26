package com.gt.base.lang;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class RuntimeTest {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        // 虚拟机处理器数量
        int count = runtime.availableProcessors();
        System.out.println(count);

        // 可用内存量
        long freeMemory = runtime.freeMemory();
        System.out.println(freeMemory);

        long max = runtime.maxMemory();
        System.out.println(max);

        long totalMemory = runtime.totalMemory();
        System.out.println(totalMemory);
    }
}
