package com.gt.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author GTsung
 * @date 2022/1/10
 */
public class FindTree {

    public static void main(String[] args) {

    }


    //////////////////////////////////////////////////////

    /**
     * 找最低公共祖先, o1与o2为root树中的节点
     */
    private static TreeNode lowestCommonAncestors(TreeNode root, TreeNode o1, TreeNode o2) {
        if (root == null || root == o1 || root == o2) {
            return root;
        }
        TreeNode left = lowestCommonAncestors(root.left, o1, o2);
        TreeNode right = lowestCommonAncestors(root.right, o1, o2);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /////////////////////////////////////////////////////

    /**
     * 序列化与反序列化
     */

    private static String serial(TreeNode node) {
        if (node == null) {
            return "#_";
        }
        String res = node.value + "_";
        res += serial(node.left);
        res += serial(node.right);
        return res;
    }

    // 反序列化
    private static TreeNode reconByString(String str) {
        String[] values = str.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i != values.length; i++) {
            queue.add(values[i]);
        }
        return reconOrder(queue);
    }

    private static TreeNode reconOrder(Queue<String> queue) {
        String value = queue.poll();
        if ("#".equals(value)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = reconOrder(queue);
        node.right = reconOrder(queue);
        return node;
    }


}
