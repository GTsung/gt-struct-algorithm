package com.gt.jdk8.test03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GTsung
 * @date 2022/4/4
 */
public class StreamTest03 {

    public static void main(String[] args) {
        Trader trader1 = new Trader("rose", "Shanghai");
        Trader trader2 = new Trader("lucy", "Beijing");
        Trader trader3 = new Trader("jack", "Hong Kong");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(trader1, 2011, 300),
                new Transaction(trader2, 2012, 1000),
                new Transaction(trader2, 2012, 400),
                new Transaction(trader3, 2012, 500),
                new Transaction(trader3, 2012, 700)
        );

        List<Transaction> transaction01s = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        List<String> cities = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());

        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Beijing".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        String reduce = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
//                .reduce("", (n1, n2) -> n1 + "," + n2);


    }
}
