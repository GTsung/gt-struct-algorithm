package com.gt.algorithm.array;

/**
 * @author GTsung
 * @date 2022/1/9
 */
public class XorArray {


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 2, 5, 1};

//        xorOne(arr);

        int[] brr = {3, 5, 6, 3, 5, 7};
        xorTwo(brr);

    }

    // 在一个数组中，只有一个数存在奇次个，找出这个数，
    // 时间复杂度要求O(n)，空间复杂度O(1)
    private static void xorOne(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }
        System.out.println(res);
    }

    // 在一个数组中，有两种数出现了奇数次，其他数出现偶数次，找到这两个数
    // 时间复杂度O(n) 空间复杂度O(1)
    private static void xorTwo(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }
        // 此时res = A ^ B; 且A != B; res != 0;
        // res肯定某位置上为1，A与B肯定在某一个位上的值不一致
        // 找到最右位的1
        // 假如res 为  00110101
        // ~res + 1 = 11001011
        // res & (~res + 1) = 00000001 ---> 提取出了最右端的1
        int rightOne = res & (~res + 1);
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            // 如果此时在某个位上依然异或为1，那么arr[i]就是另一个出现奇数次的数
            // arr[i] & (00000001) ---> 如果异或成功为1，那么arr[i]为另一个数
            if ((arr[i] & rightOne) == 1) {
                onlyOne ^= arr[i]; 
            }
        }
        System.out.println(onlyOne + "," + (res ^ onlyOne));
    }
}
