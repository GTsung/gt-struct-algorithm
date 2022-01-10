package com.gt.algorithm.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author GTsung
 * @date 2022/1/10
 */
public class PrintTree {

    public static void main(String[] args) {
        // 深度遍历即先序遍历
        // 宽度遍历
    }

    /**
     * 宽度遍历，借助队列打印
     *
     * @param root
     */
    private static void wide(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    // 找出最大宽度
    private static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        Map<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(root, 1);
        // 当前层
        int curLevel = 1;
        // 当前层节点个数
        int curLevelNodes = 0;
        int max = -1;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            // 是当前层
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                // 统计上一层的最大节点数
                max = Math.max(max, curLevelNodes);
                curLevel++; // 转到下一层
                curLevelNodes = 1; // 新层的节点个数初始化
            }
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.offer(cur.right);
            }
        }

        return max;

    }
}
