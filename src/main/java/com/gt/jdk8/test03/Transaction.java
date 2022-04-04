package com.gt.jdk8.test03;

import lombok.Getter;
import lombok.ToString;

/**
 * @author GTsung
 * @date 2022/4/4
 */
@Getter
@ToString
public class Transaction {

    private final Trader trader;

    private final int year;

    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
}
