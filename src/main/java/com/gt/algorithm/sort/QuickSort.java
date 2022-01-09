package com.gt.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序 O(nlogn)
 * 随机选取一个基准数，划分数组的小于等于大于区域
 * 将小于区域左移并置换 最后将基准数放入到小于区域的前一位，返回基准数的下标
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
        if (left < right) {
            // 随机选择一个下标作为基准(在分区方法中，这个置换到最右侧的元素被指定为基准数)
            swap(arr, left + ((int) (Math.random() * (right - left + 1))), right);
            // 分区，返回的数组个数为2
            int p = partition(arr, left, right);
            quickSort(arr, left, p - 1);
            quickSort(arr, p + 1, right);
        }
    }

    // 划分大于等于小于区域，返回等于区域下标
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        // 小于基数的下标
        int less = left - 1;
        // 中间区域，如果中间区域的值小于pivot则小于区域的下标向前移动，当前下标的值与小于区域的值互换，
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                less++;
                swap(arr, less, j);
            }
        }
        // 最后将选中的数放到小于区域的下一位
        swap(arr, less + 1, right);
        return (less + 1);
    }

    private static void swap(int[] arr, int i, int j) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
