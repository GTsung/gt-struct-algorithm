package com.gt.algorithm.array;

/**
 * 递归获取最大值O(logN)
 * @author GTsung
 * @date 2022/1/9
 */
public class FindMax {

    public static void main(String[] args) {
        int[] arr = {12, 34, 11, 2, 7, 53, 1, 25, 25, 19};

        int max = findMax(arr, 0, arr.length - 1);
        System.out.println(max);
    }

    /**
     * 针对递归的master公式
     * T(N) = a * T(N/b) + O(N^d)
     * 其中a为总共调了几次，b为等分的子递归个数，后面的为递归额外需要的时间复杂度
     * (1) log(b, a) > d ----> 复杂度为O(N^log(b,a))
     * (2) log(b, a) = d ----> 复杂度为O(N^d * logN)
     * (3) log(b, a) < d ----> 复杂度为O(N^d)
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int findMax(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int mid = left + ((right - left) >>> 1);
        int leftMax = findMax(arr, left, mid);
        int rightMax = findMax(arr, mid + 1, right);
        return Math.max(leftMax, rightMax);
    }
}
