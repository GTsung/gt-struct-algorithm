package com.gt.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序 O(n^2)
 * @author GTsung
 * @date 2021/12/16
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {12, 34, 11, 2, 7, 53, 1, 25, 25, 19};
        selectionSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    /**
     * 选择排序要领：
     * 1.先选择一个下标，比如从0开始，设这个下标为最小值所在下标
     * 2.向后遍历，找到比这个最小值所在下标的值还小的下标
     * 3.交换下标位置的数
     * @param arr
     */
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 设最小值所在下标为i
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 如果下标j所在值小于最小下标的值，那么将j赋值给min
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            // min 与 i 不相等则互换值
            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
