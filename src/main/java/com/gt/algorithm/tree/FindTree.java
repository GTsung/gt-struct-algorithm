package com.gt.algorithm.tree;

import java.util.*;

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

    /**
     * 根據先序遍歷的數組和中序遍歷的數組生成一個樹
     */


    /**
     * 給定一個整數n，生成不同的二叉樹
     * 生成樹首先生成其左子樹和右子樹，然後拼接
     * 對於整數i(i<n)，它的左子樹為[0, i-1];右子樹為[i+1, n]
     */
    public List<TreeNode> generateTree(int n) {
        if (n == 0) return new LinkedList<>();
        return generate(1, n);
    }

    private List<TreeNode> generate(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generate(start, i - 1);
            List<TreeNode> rightTrees = generate(i + 1, end);
            // 拼接
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

    /**
     * 搜索二叉樹錯位，[2,1,3] ---> 應該為[1, 2, 3]
     * 現在將tree修復，不改變結構
     * 可以中序遍歷，如果排序正常，則返回否則，將不在正確位置的節點值互換
     */
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);
        TreeNode x = null;
        TreeNode y = null;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).value > list.get(i + 1).value) {
                y = list.get(i + 1);
                if (x == null) {
                    x = list.get(i);
                }
            }
        }
        if (x != null && y != null) {
            int tmp = x.value;
            x.value = y.value;
            y.value = tmp;
        }
    }

    // 中序遍歷
    private void dfs(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        dfs(root.left, list);
        list.add(root);
        dfs(root.right, list);
    }

    /**
     * 給定先序遍歷數組及中序遍歷數組，還原一顆樹
     */
    static Map<Integer, Integer> indexMap;

    private static void returnTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        buildTree(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    private static TreeNode buildTree(int[] preorder, int pre_left, int pre_right,
                                      int[] inorder, int in_left, int in_right) {
        if (pre_left > pre_right) {
            return null;
        }
        int pre_root = pre_left;
        int in_root = indexMap.get(preorder[pre_root]);

        TreeNode root = new TreeNode(preorder[pre_root]);
        int size_left = in_root - in_left;
        root.left = buildTree(preorder, pre_left + 1, pre_left + size_left, inorder, in_left, in_root - 1);
        root.right = buildTree(preorder, pre_left + size_left + 1, pre_right, inorder, in_root + 1, in_right);
        return root;
    }


}
