package com.gt.algorithm.offer;

import com.gt.algorithm.link.LinkNode;
import com.gt.algorithm.tree.TreeNode;

import java.util.*;

/**
 * @author GTsung
 * @date 2022/2/21
 */
public class Sample01 {

    /**
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
     * 请找出数组中任意一个重复的数字。
     */
    public int findNum(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;
    }

    public int findNum2(int[] nums) {
        for (int i = 0; i < nums.length; ) {
            if (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
                continue;
            }
            i++;
        }
        return -1;
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。
     * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public static boolean hasNum(int[][] arr, int k) {
        int wide = arr.length;
        int deep = arr[0].length;
        for (int i = wide - 1, j = 0; i >= 0 && j < deep; ) {
            if (arr[i][j] > k) {
                i--;
            } else if (arr[i][j] < k) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static String replaceSpace(String str) {
        if (str == null || str.length() == 0) return str;
        int length = str.length();
        char[] chars = str.toCharArray();
        char[] newChars = new char[length * 3];
        int size = 0;
        for (int i = 0; i < length; i++) {
            if (chars[i] == ' ') {
                newChars[size++] = '%';
                newChars[size++] = '2';
                newChars[size++] = '0';
            } else {
                newChars[size++] = chars[i];
            }
        }
        return new String(newChars, 0, size);
    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     */
    public static int[] printReverse(LinkNode nodes) {
        if (nodes == null) return new int[0];
        LinkedList<Integer> stack = new LinkedList<>();
        while (nodes != null) {
            stack.push(nodes.val);
        }
        int i = 0;
        int[] arr = new int[stack.size()];
        while (!stack.isEmpty()) {
            arr[i++] = stack.pop();
        }
        return arr;
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字
     */
    static Map<Integer, Integer> map = new HashMap<>();

    public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        return build(preOrder, 0, preOrder.length,
                inOrder, 0, inOrder.length);
    }

    private static TreeNode build(int[] preOrder, int preStart, int preEnd,
                                  int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preOrder[preStart];
        int in = map.get(rootVal);
        int leftNum = in;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preOrder, preStart + 1, preStart + leftNum,
                inOrder, inStart, in - 1);
        root.right = build(preOrder, preStart + leftNum + 1, preEnd,
                inOrder, in + 1, inEnd);
        return root;
    }

    /**
     * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
     * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
     */
    public static class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void offer(int val) {
            stack1.push(val);
        }

        public Integer poll() {
            if (stack2.isEmpty()) {
                while(!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (!stack2.isEmpty()) {
                return stack2.pop();
            }
            return null;
        }
    }

    /**
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。
     * 斐波那契数列的定义如下：
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     */
    public static int fib(int n) {
        if (n < 2) return n;
        int a = 0,  b = 0, sum = 1;
        for (int i = 2; i <= n;i++) {
            a = b;
            b = sum;
            sum = a + b;
        }
        return sum;
    }

    public static void main(String[] args) {

    }
}
