package com.gt.jdk8.test04;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author GTsung
 * @date 2022/4/4
 */
public class StreamTest04 {

    public static void main(String[] args) {
        iterateTest();

        fb();

        generateFb();
    }

    private static void generateFb() {
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nexValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nexValue;
                return oldPrevious;
            }
        };

        IntStream.generate(fib)
                .limit(10)
                .forEach(System.out::println);
    }

    private static void generateTest() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    private static void iterateTest() {
        // iterate指定一个初始值，后面的UnaryOperator是对前一个值进行处理后得到新的值
        // 无界的流，因此截断
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    // 斐波那契
    private static void fb() {
        Stream.iterate(new int[] {0, 1}, t -> new int[] {t[1], t[0] + t[1]})
                .limit(20)
                .forEach(System.out::println);
    }
}
