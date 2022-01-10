package com.gt.algorithm.tree;

/**
 * @author GTsung
 * @date 2022/1/10
 */
public class TreeNode {

    TreeNode left;
    TreeNode right;
    int value;
    int height;

    public TreeNode(TreeNode left, TreeNode right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.height = 0;
    }

    public TreeNode(int value) {
        this(null, null, value);
    }
}
