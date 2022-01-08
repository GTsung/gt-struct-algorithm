package com.gt.algorithm.sort;

import java.util.Arrays;

/**
 * @author GTsung
 * @date 2021/12/15
 */
public class BubbleSort {

    // 冒泡排序O(n^2)
    // 前一个数与后一个数比较，前一个数大于后一个数则交换
    // 总共需要n-1轮(n为数组长度)，每轮比较n-i-1次(i为当前轮数)
    public static void main(String[] args) {
        int[] arr = {12, 34, 11, 2, 7, 53, 1, 25, 25, 19};
        int[] brr = bubbleSort2(arr);
        Arrays.stream(brr).forEach(System.out::println);
    }

    /**
     * a[] = { 51, 32, 14, 25, 9 };  n = 5;
     * 第一次i=0; 需要至多比较4次
     * 第二次i=1; 需要至多比较3次(最后一位已经是最大，剩下4个数比较)
     * 第三次i=2; 需要至多比较2次(最后两位已经排号，剩下3个数比较)
     * 第四次i=3; 需要至多比较1次(剩下2个数进行比较)
     * 排序完成
     * 总共n-1轮比较，每轮比较次数为n-1-i
     *
     * @param arr
     * @return
     */
    private static int[] bubbleSort(int[] arr) {
        // i为末尾已排好序的个数
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }

    // 将排好序的最大数放在最后一位
    private static int[] bubbleSort2(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    // 异或运算: a^0 = a; a^a = 0;
    // a ^= b; ---> a = a^b
    // b ^= a; ---> b = b^(a^b) = 0 ^ a = a;
    // a ^= b; ---> a = (a ^ b) ^ a = b;
    // 异或运算能交换数值的前提: a与b所在内存不是一块
    private static void swap(int[] arr, int j, int k) {
        arr[j] ^= arr[k];
        arr[k] ^= arr[j];
        arr[j] ^= arr[k];
    }
}
