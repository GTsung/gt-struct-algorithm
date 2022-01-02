package com.gt.algorithm.array;

import java.util.Arrays;

/**
 * @author GTsung
 * @date 2021/12/15
 */
public class ArrayRemoveValue {

    /**
     * 给定一个数组和一个值，在原数组删除等于该值的元素，返回新的数组长度
     */

    public static void main(String[] args) {
        int[] a = {12, 1, 34, 5, 8, 9};
        ArrayRemoveValue arrayRemoveValue = new ArrayRemoveValue();
        System.out.println(arrayRemoveValue.printNewLengthAfterDel(a, 9));
        Arrays.stream(a).forEach(System.out::println);
    }

    private int printNewLengthAfterDel(int[] a, int v) {
        int n = a.length, j = 0;
        for (int i = 0; i < n; i++) {
            // 当下标i处的元素与给定值相等，这时候只移动i
            if (a[i] == v) {
                continue;
            }
            // j下标小于等于i，小于i说明出现了指定value，
            // 这时候将下一个不等于指定value的i下标对应值挪到下标j处,即删除了指定value
            a[j] = a[i];
            j++; // j递增，新数组长度
        }
        return j;
    }
}
