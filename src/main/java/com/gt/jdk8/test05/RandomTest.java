package com.gt.jdk8.test05;

import com.gt.jdk8.test03.Trader;

import java.util.*;

/**
 * @author GTsung
 * @date 2022/4/4
 */
public class RandomTest {

    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(random.nextInt(10));

        List<Trader> traders = Arrays.asList(new Trader("jane", "Beijing")
                , new Trader("lucy", "Shanghai")
        );
        Map<String, Trader> traderMap = traders.stream()
                .collect(HashMap::new, (map, t) -> map.put(t.getName(), t), HashMap::putAll);
    }

}
