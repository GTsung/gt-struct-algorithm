package com.gt.algorithm.array;

/**
 * @author GTsung
 * @date 2022/1/18
 */
public class ArrayOffer {

    public static void main(String[] args) {
//        findDuplication();
        findInTwoDimensionalArray();
    }

    /**
     * 給定一個數組，長度為n,數組元素在0-n-1閒，找出其中重複的數字
     */
    private static void findDuplication() {
        int[] arr = {4, 3, 1, 0, 2, 5, 6};
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] != i) {
                if (arr[i] == arr[arr[i]]) {
                    System.out.println(arr[i]);
                    return;
                }
                int temp = arr[i];
                arr[i] = arr[temp];
                arr[temp] = temp;
            }
        }
    }

    /**
     * 二維數組每行遞增 每列遞增，找出一個數是否存在
     * 1   2   8   9
     * 2   4   9   12
     * 4   7   10  13
     * 6   8   11  15
     */
    private static void findInTwoDimensionalArray() {
        int[][] arr = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int key = 18;
        // 水平的長度
        int wide = arr[0].length;
        // 豎直方向的長度
        int deep = arr.length;
        // 遞增的二維數組可以作為二叉樹
        for (int i = 0, j = wide - 1; arr[i][j] != key; ) {
            if (key < arr[i][j]) {
                j--;
                if (j == -1) {
                    System.out.println(String.format("數組中不存在%d", key));
                    return;
                }
            } else if (key > arr[i][j]) {
                i++;
                if (i == deep) {
                    System.out.println(String.format("數組中不存在%d", key));
                    return;
                }
            }
        }
        System.out.println(String.format("數組中存在%d", key));
    }


}
