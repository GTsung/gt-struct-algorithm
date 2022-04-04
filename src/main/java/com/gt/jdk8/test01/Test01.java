package com.gt.jdk8.test01;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author GTsung
 * @date 2022/4/4
 */
public class Test01 {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                Apple.builder().colour("green").weight(12).build(),
                Apple.builder().colour("red").weight(13).build());
        List<Apple> appleList = filter(apples, a -> "green".equals(a.getColour()));
        appleList.forEach(System.out::println);

        forEach(apples, System.out::println);
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> p) {
        if (list == null || list.size() <= 0) {
            return Lists.newArrayList();
        }
        return list.stream().filter(p).collect(Collectors.toList());
    }

    private static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        return list.stream().map(f).collect(Collectors.toList());
    }

}
