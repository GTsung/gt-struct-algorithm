package com.gt.algorithm.zuo.lesson02;

import com.gt.algorithm.tree.TreeNode;

/**
 * @author GTsung
 * @date 2022/3/3
 */
public class TreeTest {

    public static void main(String[] args) {

    }

    public static class Info {
        public int distance;
        public int height;

        public Info(int distance, int height) {
            this.distance = distance;
            this.height = height;
        }
    }

    public static Info getMaxDistance(TreeNode root) {
        if (root == null) return new Info(0, 0);
        Info left = getMaxDistance(root.left);
        Info right = getMaxDistance(root.right);
        int p1 = left.distance;
        int p2 = right.distance;
        int p3 = left.height + 1 + right.height;
        int maxDistance = Math.max(p3, Math.max(p1, p2));
        int height = Math.max(left.height, right.height) + 1;
        return new Info(maxDistance, height);
    }
}
