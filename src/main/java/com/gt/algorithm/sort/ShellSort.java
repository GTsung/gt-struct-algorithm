package com.gt.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序，利用间隔进行插入排序 O(n^2)
 *
 * @author GTsung
 * @date 2021/12/19
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {12, 34, 11, 2, 7, 53, 1, 25, 25, 19};
        shellSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void shellSort(int[] arr) {
        int k;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                for (k = i; k >= gap && arr[k - gap] > tmp; k -= gap) {
                    arr[k] = arr[k - gap];
                }
                arr[k] = tmp;
            }
        }
    }
}
