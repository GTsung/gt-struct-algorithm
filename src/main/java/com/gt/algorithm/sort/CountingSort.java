package com.gt.algorithm.sort;

/**
 * 计数排序: 不再比较数据
 *
 * @author GTsung
 * @date 2022/1/9
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] ages = {12, 12, 1, 10, 10, 32, 12, 23, 14, 52, 25, 19};
        // ages数组中的大小区间在1-52
        int[] counts = new int[53];
        for (int i = 0; i < ages.length; i++) {
            counts[ages[i]]++;
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] != 0) {
                for (int j = 0; j < counts[i]; j++) {
                    System.out.println(i);
                }
            }
        }
    }
}
