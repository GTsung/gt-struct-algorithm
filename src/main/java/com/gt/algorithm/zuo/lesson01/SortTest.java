package com.gt.algorithm.zuo.lesson01;

import java.util.Arrays;

/**
 * @author GTsung
 * @date 2022/3/2
 */
public class SortTest {

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 2, 9, 7, 0};
//        bubbleSort(arr);
//        choseSort(arr);
//        insertSort(arr);
        shellSort(arr);
        Arrays.stream(arr).forEach(a -> System.out.print(a + " "));
    }

    // 希爾排序
    private static void shellSort(int[] arr) {
        int j;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    // 插入排序
    private static void insertSort(int[] arr) {
        int j;
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for (j = i; j > 0 && temp < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    // 選擇排序
    private static void choseSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            if (index != i) {
                swap(arr, i, index);
            }
        }
    }

    // 冒泡排序
    private static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j + 1] < arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }


}
