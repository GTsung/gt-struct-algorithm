package com.gt.tree;

import com.gt.algorithm.tree.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author GTsung
 * @date 2022/1/30
 */
public class TreeTest {

    /**
     * 判斷是否為二叉搜索樹
     * 1.中序遍歷，左中右，從小到大排列
     */
    // 定義一個最小值
    private Integer preVal = Integer.MIN_VALUE;

    public boolean isBST(TreeNode root) {
        if (root == null) return true;
        boolean left = isBST(root.left);
        if (!left) return false;

        if (root.value < preVal) {
            return false;
        } else {
            preVal = root.value;
        }
        return isBST(root.right);
    }

    /**
     * 2.利用遞歸返回一個類
     */
    static class BSTResult {
        boolean isBST;
        int min;
        int max;

        public BSTResult(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public BSTResult isBST2(TreeNode root) {
        if (null == root) return null;
        BSTResult leftResult = isBST2(root.left);
        BSTResult rightResult = isBST2(root.right);
        // min與max用於返回
        int max = root.value;
        int min = root.value;
        if (leftResult != null) {
            min = Math.min(leftResult.min, min);
            max = Math.max(leftResult.max, max);
        }
        if (rightResult != null) {
            min = Math.min(rightResult.min, min);
            max = Math.max(rightResult.max, max);
        }
        // isBST也用於返回
        boolean isBST = true;
        if (leftResult != null && (!leftResult.isBST || leftResult.max > root.value)) {
            isBST = false;
        }
        if (rightResult != null && (!rightResult.isBST || rightResult.min < root.value)) {
            isBST = false;
        }
        return new BSTResult(isBST, min, max);
    }

    @Test
    public void testIsBST() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(5);
        root.right = new TreeNode(25);
        assert isBST(root);
    }

    /**
     * 序列化一棵樹
     *
     * @param root
     * @return
     */
    public String serializeTree(TreeNode root) {
        if (root == null) {
            return "#_";
        }
        String res = root.value + "_";
        res += serializeTree(root.left);
        res += serializeTree(root.right);
        return res;
    }

    /**
     * 反序列化一棵樹
     *
     * @param root
     * @return
     */
    public TreeNode deserializeTree(String root) {
        if (root == null || root.length() == 0) {
            return null;
        }
        String[] res = root.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < res.length; i++) {
            queue.offer(res[i]);
        }
        return deTree(queue);
    }

    private TreeNode deTree(Queue<String> queue) {
        String rootVal = queue.poll();
        if ("#".equals(rootVal)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(rootVal));
        root.left = deTree(queue);
        root.right = deTree(queue);
        return root;
    }

    /**
     * 深度遍歷(前序遍歷)
     */
    public void printInDeep(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        printInDeep(root.left);
        printInDeep(root.right);
    }

    /**
     * 寬度遍歷(利用隊列)
     */
    public void printInWide(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 一棵樹的最大寬度
     */
    public int mostWide(TreeNode root) {
        if (root == null) return 0;
        int currentLevel = 1;
        int currentNodes = 0;
        int maxNode = 0;
        Map<TreeNode, Integer> levelNodeMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        levelNodeMap.put(root, currentLevel);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (levelNodeMap.get(node) == currentLevel) {
                currentNodes++;
            } else {
                maxNode = Math.max(maxNode, currentNodes);
                currentLevel++;
                currentNodes = 1;
            }
            if (node.left != null) {
                queue.offer(node.left);
                levelNodeMap.put(node.left, currentLevel + 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                levelNodeMap.put(node.right, currentLevel + 1);
            }
        }
        return maxNode;
    }

    /**
     * 一棵樹是否為完全二叉樹
     * 1.有右節點無左節點則不是
     * 2.如果當前節點是沒有雙全子節點的節點，則之後的節點均為葉子節點(寬度遍歷，無子節點)
     */
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        // 是否為葉子節點
        boolean leaf = false;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode left = null;
        TreeNode right = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            left = node.left;
            right = node.right;
            if ((right != null && left == null)
                    || (leaf && (left != null || right != null))) {
                return false;
            }
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
            // 這裏是或的關係，表明當前節點node只有一個節點，其後的節點均為子節點
            if (left == null || right == null) {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 滿二叉樹，節點個數為層數的2次冪減1
     */
    static class FullTreeResult {
        int layer;
        int sum;

        public FullTreeResult(int layer, int sum) {
            this.layer = layer;
            this.sum = sum;
        }
    }

    public FullTreeResult isFullTree(TreeNode root) {
        if (root == null) return new FullTreeResult(0, 0);
        FullTreeResult leftResult = isFullTree(root.left);
        FullTreeResult rightResult = isFullTree(root.right);
        int layer = Math.max(leftResult.layer, rightResult.layer) + 1;
        int sum = leftResult.sum + rightResult.sum + 1;
        return new FullTreeResult(layer, sum);
    }

    /**
     * 判斷是否為平衡二叉樹
     */
    static class BalanceResult {
        boolean balanced;
        int height;

        public BalanceResult(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }
    }

    public BalanceResult isBalancedTree(TreeNode root) {
        if (root == null) return new BalanceResult(true, 0);
        BalanceResult leftRes = isBalancedTree(root.left);
        BalanceResult rightRes = isBalancedTree(root.right);
        int height = Math.max(leftRes.height, rightRes.height) + 1;
        boolean balanced = leftRes.balanced && rightRes.balanced && (Math.abs(leftRes.height - rightRes.height) < 2);
        return new BalanceResult(balanced, height);
    }

    /**
     * 找公共祖先
     */
    public TreeNode findCommonParent(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || root == node1 || root == node2) return root;
        TreeNode left = findCommonParent(root.left, node1, node2);
        TreeNode right = findCommonParent(root.right, node1, node2);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
