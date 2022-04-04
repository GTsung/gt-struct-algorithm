package com.gt.jdk8.test02;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author GTsung
 * @date 2022/4/4
 */
public class StreamTest02 {

    public static void main(String[] args) {
        flatMapTest01();
    }

    private static void flatMapTest02() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        // 获取的是Stream的列表
        List<Stream<int[]>> collect = numbers1.stream()
                // i -> numbers2.stream().map(j -> new int[]{i, j}) 返回的是一个Stream
                .map(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        // 使用flatMap将流的内容转换成为一个流
        List<int[]> ints = numbers1.stream()
                // flatMap将Stream的内容重新组合为一个流
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());


    }

    private static void flatMapTest01() {
        String[] words = {"hello", "world"};
        List<String> singleWords = Arrays.stream(words)
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        singleWords.forEach(System.out::println);
    }
}
