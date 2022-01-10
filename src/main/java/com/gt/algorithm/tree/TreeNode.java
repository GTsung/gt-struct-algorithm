package com.gt.algorithm.tree;

/**
 * @author GTsung
 * @date 2022/1/10
 */
public class TreeNode<T extends Comparable<? super T>> {

    TreeNode left;
    TreeNode right;
    T value;

    public TreeNode(TreeNode left, TreeNode right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode(T value) {
        this(null, null, value);
    }
}
