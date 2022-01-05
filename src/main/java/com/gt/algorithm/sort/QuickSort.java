package com.gt.algorithm.sort;

import java.util.Arrays;

/**
 * @author GTsung
 * @date 2022/1/5
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {12, 34, 11, 2, 7, 53, 1, 25, 25, 19};
        quickSort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void quickSort(int[] arr, int left, int right) {
        Integer chosen = median3(arr, left, right);
        int i = left, j = right - 1;
        for (; ; ) {
            while (arr[++i] < chosen) {
            }
            while (arr[--j] > chosen) {
            }
            if (i < j) {
                swap(arr, i, j);
            } else {
                break;
            }
        }
        swap(arr, i, right - 1);
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    private static Integer median3(int[] arr, int left, int right) {
        int center = (left + right) / 2;
        if (arr[center] < arr[left]) swap(arr, left, center);
        if (arr[right] < arr[left]) swap(arr, left, right);
        if (arr[right] < arr[center]) swap(arr, center, right);
        swap(arr, center, right - 1);
        return arr[right - 1];
    }

    private static void swap(int[] arr, int i, int j) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
