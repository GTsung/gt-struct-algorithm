package com.gt.algorithm.sort;

import com.google.common.collect.Lists;

import java.util.List;


/**
 * @author GTsung
 * @date 2022/1/5
 */
public class QuickSortDemo {

    public static void main(String[] args) {
        List<Integer> sortList = Lists.newArrayList();
        sortList.add(12);
        sortList.add(34);
        sortList.add(11);
        sortList.add(2);
        sortList.add(7);
        sortList.add(53);
        sortList.add(1);
        sortList.add(25);
        sortList.add(25);
        sortList.add(19);

        List<Integer> sortedList = sortMethod(sortList);
        sortedList.forEach(System.out::println);
    }

    private static List<Integer> sortMethod(List<Integer> sortList) {
        if (sortList.size() <= 0) {
            return sortList;
        }
        List<Integer> smaller = Lists.newArrayList();
        List<Integer> same = Lists.newArrayList();
        List<Integer> larger = Lists.newArrayList();

        int chosenNum = sortList.get(0);
        for (Integer num: sortList) {
            if (num < chosenNum) {
                smaller.add(num);
            } else if (num > chosenNum) {
                larger.add(num);
            } else {
                same.add(num);
            }
        }

        sortMethod(smaller);
        sortMethod(larger);
        sortList.clear();
        sortList.addAll(smaller);
        sortList.addAll(same);
        sortList.addAll(larger);
        return sortList;
    }
}
