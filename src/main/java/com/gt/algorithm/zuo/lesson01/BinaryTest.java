package com.gt.algorithm.zuo.lesson01;

/**
 * @author GTsung
 * @date 2022/3/2
 */
public class BinaryTest {

    public static void main(String[] args) {
        int[] arr = {1, 4, 5, 6, 7, 7, 9};
        System.out.println(binarySearchSelf(arr, 2));

        // 找到大於等於某值的最左下標
        int[] brr = {1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 5, 5, 5, 6, 6, 7};
        System.out.println(binaryIndex(brr, 4));

        // 找到一個局部最小值的下標
        int[] crr = {5, 3, 6, 8, 9, 7, 10};
        System.out.println(binaryFind(crr));
    }

    private static int binaryFind(int[] crr) {
        if (crr[0] < crr[1]) return crr[0];
        if (crr[crr.length - 1] < crr[crr.length - 2]) return crr[crr.length - 1];
        int left = 0, right = crr.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (crr[mid] > crr[mid - 1]) right = mid - 1;
            else if (crr[mid] > crr[mid + 1]) left = mid + 1;
            else return mid;
        }
        return left;
    }

    private static int binaryIndex(int[] brr, int num) {
        int left = 0, right = brr.length - 1;
        int index = 0;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (brr[mid] < num) left = mid + 1;
            else if (brr[mid] >= num) {
                index = mid;
                right = mid;
            }
        }
        return index;
    }

    private static boolean binarySearchSelf(int[] arr, int num) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (arr[mid] > num) right = mid - 1;
            else if (arr[mid] < num) left = mid + 1;
            else return true;
        }
        return false;
    }
}
