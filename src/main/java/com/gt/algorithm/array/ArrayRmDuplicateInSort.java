package com.gt.algorithm.array;

import java.util.Arrays;

/**
 * @author GTsung
 * @date 2021/12/15
 */
public class ArrayRmDuplicateInSort {

    /**
     * 在一个有序数组中，原地删除相同出现的元素，并返回新数组长度
     */

    public static void main(String[] args) {
        ArrayRmDuplicateInSort arrayRmDuplicateInSort = new ArrayRmDuplicateInSort();
        int[] a = {11, 12, 12, 14, 15, 15};
        System.out.println(arrayRmDuplicateInSort.removeDuplicateInSort(a));
        Arrays.stream(a).forEach(System.out::println);
    }

    public int removeDuplicateInSort(int[] a) {
        int n = a.length;
        int j = 0;
        for (int i = 1; i < n; i++) {
//            if (a[i] != a[j]) {
//                a[++j] = a[i];
//            }
            if (a[i] == a[j]) {
                continue; // 只加i
            }
            a[++j] = a[i]; // 先移动j，再赋值
        }
        return j + 1; // 返回的是j+1
    }
}
