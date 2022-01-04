package com.gt.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序 O(nlogn)
 *
 * @author GTsung
 * @date 2022/1/4
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {12, 34, 11, 2, 7, 53, 1, 25, 25, 19};
        mergeSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void mergeSort(int[] arr) {
        merge(arr, 0, arr.length - 1, new int[arr.length]);
    }

    private static void merge(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            merge(arr, left, mid, temp);
            merge(arr, mid + 1, right, temp);
            sort(arr, left, mid + 1, right, temp);
        }
    }

    private static void sort(int[] arr, int leftPos, int rightPos, int rightEnd, int[] temp) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        // 需要排序的元素个数
        int elementNum = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            temp[tempPos++] = arr[leftPos] < arr[rightPos] ? arr[leftPos++] : arr[rightPos++];
        }
        while (leftPos <= leftEnd) {
            temp[tempPos++] = arr[leftPos++];
        }
        while (rightPos <= rightEnd) {
            temp[tempPos++] = arr[rightPos++];
        }
        // 只需要复制排列元素个数次即可，rightPos、leftPos都已经发生了变化，因此从最右端开始排
        for (int i = 0; i < elementNum; i++, rightEnd--) {
            arr[rightEnd] = temp[rightEnd];
        }

    }
}
