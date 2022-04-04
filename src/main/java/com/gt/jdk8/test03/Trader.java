package com.gt.jdk8.test03;

import lombok.Getter;
import lombok.ToString;

/**
 * @author GTsung
 * @date 2022/4/4
 */
@Getter
@ToString
public class Trader {

    private final String name;

    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
