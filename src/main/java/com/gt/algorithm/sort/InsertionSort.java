package com.gt.algorithm.sort;

import java.util.Arrays;

/**
 * 直接插入排序 O(n^2)
 *
 * @author GTsung
 * @date 2021/12/19
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {12, 34, 11, 2, 7, 53, 1, 25, 25, 19};
        insertionSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    /**
     * 插入排序将数组分为两组，一组有序数组，一组是无序组
     * 从无序组开始对有序组分别比较，如果小于有序组，则互换位置
     *
     * @param arr
     */
    private static void insertionSort(int[] arr) {
        int j;
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            // 此处开始对下标为i的元素与前边下标元素进行比对
            for (j = i; j > 0 && arr[j-1] > tmp; j--) {
                arr[j] = arr[j-1];
            }
            arr[j] = tmp;
        }
    }


}
