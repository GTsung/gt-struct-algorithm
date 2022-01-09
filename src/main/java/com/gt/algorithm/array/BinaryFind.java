package com.gt.algorithm.array;

/**
 * @author GTsung
 * @date 2022/1/9
 */
public class BinaryFind {

    // 二分查找，前提数组有序
    // O(logN)
    public static void main(String[] args) {
        int[] arr = {5, 11, 16, 23, 31, 36, 53, 59};
        System.out.println(binarySearch(15, arr));

        int[] brr = {1, 1, 1, 1, 2, 2, 2, 5, 5, 5, 6, 6, 7, 7};
        System.out.println(searchLeftIndex(3, brr));
    }

    /**
     * 二分查找 O(logN)
     *
     * @param num
     * @param arr
     * @return
     */
    private static boolean binarySearch(int num, int[] arr) {
        boolean flag = false;
        int left = 0, right = arr.length - 1;
        // left <= right ----> 存在两个相等的情况，直到left比right大为止
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > num) {
                right = mid - 1;
            } else if (arr[mid] < num) {
                left = mid + 1;
            } else {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 找到大于等于某个数的最左侧的位置
     * {1, 1, 1, 1, 2, 2, 2, 5, 5, 5, 6, 6, 7, 7} ---> 找出大于2的最左侧位置下标
     */
    private static int searchLeftIndex(int num, int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] >= num) {
                right = mid - 1;
            } else if (arr[mid] < num) {
                left = mid + 1;
            }
        }
        return left;
    }

    // 局部最小问题: 无序数组中某下标的数小于其左侧亦小于其右侧的数
    // 分析: 先查看arr[0] < arr[1] 或 arr[length-1] < arr[length-2] ----> 这时局部最小即为arr[0] 或arr[length-1]
    // 否则之间必定存在一个局部最小值

}
