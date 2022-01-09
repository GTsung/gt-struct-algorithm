package com.gt.algorithm.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author GTsung
 * @date 2022/1/9
 */
public class TopK {

    // 给定一个数组，找出最大的前K个数
    // 典型TopK问题，当需要找前K大的数时使用小根堆

    public static void main(String[] args) {
        int[] arr = {12, 34, 11, 2, 7, 53, 1, 25, 25, 19};
        int[] res = topK(arr, 4);
        Arrays.stream(res).forEach(System.out::println);
    }

    private static int[] topK(int[] arr, int k) {
        // 默认小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int i = 0; i < arr.length; i++) {
            if (queue.size() < k) {
                queue.offer(arr[i]);
            } else {
                if (queue.peek() < arr[i]) {
                    queue.poll();
                    queue.offer(arr[i]);
                }
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }
}
