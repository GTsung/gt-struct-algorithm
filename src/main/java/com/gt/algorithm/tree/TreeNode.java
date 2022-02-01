package com.gt.algorithm.tree;

/**
 * @author GTsung
 * @date 2022/1/10
 */
public class TreeNode {

    public TreeNode left;
    public TreeNode right;
    public int value;

    public TreeNode(TreeNode left, TreeNode right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode(int value) {
        this(null, null, value);
    }
}
