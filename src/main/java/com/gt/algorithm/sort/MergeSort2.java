package com.gt.algorithm.sort;

/**
 * 小和问题
 *
 * @author GTsung
 * @date 2022/1/9
 */
public class MergeSort2 {

    // 小和问题:

    /**
     * 假如有一个数组{ 1, 3, 4, 2, 5 }，它的小和指的是:
     * 对于第一个元素 1 ---> 左侧小于 1 的为 0
     * 对于第二个元素 3 ---> 左侧小于 3 的为 1
     * 对于第三个元素 4 ---> 左侧小于 4 的为 1 + 3 = 4
     * 对于第四个元素 2 ---> 左侧小于 2 的为 1
     * 对于第五个元素 5 ---> 左侧小于 5 的为 1 + 3 + 4 + 2 = 10
     * 那么这个数组的小和即是 10 + 1 + 4 + 1 = 16
     */

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 5};
        int res = mergeSort(arr, 0, arr.length - 1);
        System.out.println(res);
    }

    private static int mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >>> 1);
        return mergeSort(arr, left, mid)
                + mergeSort(arr, mid + 1, right)
                + merge(arr, left, mid + 1, right);
    }

    private static int merge(int[] arr, int left, int rightPos, int rightEnd) {
        int[] tmp = new int[rightEnd - left + 1];
        int tmpPos = 0;
        int leftPos = left;
        int leftEnd = rightPos - 1;
        int res = 0;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            // 如果左侧小于右侧，那么右侧所有的数都比左侧大
            res += arr[leftPos] < arr[rightPos] ? arr[leftPos] * (rightEnd - rightPos + 1) : 0;
            tmp[tmpPos++] = arr[leftPos] < arr[rightPos] ? arr[leftPos++] : arr[rightPos++];
        }
        while (leftPos <= leftEnd) {
            tmp[tmpPos++] = arr[leftPos++];
        }
        while (rightPos <= rightEnd) {
            tmp[tmpPos++] = arr[rightPos++];
        }
        for (int i = 0; i < tmp.length; i++) {
            arr[left + i] = tmp[i];
        }
        return res;
    }

}