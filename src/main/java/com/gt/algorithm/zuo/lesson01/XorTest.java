package com.gt.algorithm.zuo.lesson01;

/**
 * @author GTsung
 * @date 2022/3/2
 */
public class XorTest {

    public static void main(String[] args) {
        int[] arr = {3, 3, 1, 2, 2, 4, 4, 6, 6, 7, 7, 7, 7};
        System.out.println(findOne(arr));

        int[] brr = {3, 3, 3, 4, 4, 5, 1, 1, 1, 1, 6, 6};
        findTwo(brr);
    }

    private static void findTwo(int[] brr) {
        int res = 0;
        for (int i = 0; i < brr.length; i++) {
            res ^= brr[i];
        }
        // 找到最右側為1的位
        //      res : 0010011
        // ~res + 1 : 1101101
        //        b : 0000001
        int b = res & (~res + 1);
        int one = 0;
        // 出現奇次的兩個數一定在這個位上不同
        // 遍歷所有數，這些數分爲在這個位上為0或1
        for (int i = 0; i < brr.length; i++) {
            if ((b & brr[i]) == 0) {
                one ^= brr[i];
            }
        }
        System.out.println(one + "---" + (res ^ one));
    }

    private static int findOne(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res ^= arr[i];
        }
        return res;
    }
}
