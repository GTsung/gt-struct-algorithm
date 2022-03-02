package com.gt.algorithm.zuo.lesson02;

import java.util.Arrays;

/**
 * @author GTsung
 * @date 2022/3/2
 */
public class RecursionTest {

    public static void main(String[] args) {
        int[] arr = {0, 5, 2, 3, 11, 9, 8};
        System.out.println(process(arr));

        // 歸并排序
        mergeSort(arr, 0, arr.length - 1);
        Arrays.stream(arr).forEach(a -> System.out.print(a + " "));
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left == right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] helper = new int[right - left + 1];
        int i = 0;
        int leftPos = left;
        int rightPos = mid + 1;
        while (leftPos <= mid && rightPos <= right) {
            helper[i++] = arr[leftPos] > arr[rightPos] ? arr[rightPos++] : arr[leftPos++];
        }
        while (leftPos <= mid) {
            helper[i++] = arr[leftPos++];
        }
        while (rightPos <= right) {
            helper[i++] = arr[rightPos++];
        }
        for (i = 0; i < helper.length; i++) {
            arr[left + i] = helper[i];
        }
    }


    // 遞歸求取數組最大值，分治遞歸調用
    private static int process(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        if (left == right) return arr[left];
        int mid = (right - left) / 2 + left;
        int leftMax = process(arr, left, mid);
        int rightMax = process(arr, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }
}
