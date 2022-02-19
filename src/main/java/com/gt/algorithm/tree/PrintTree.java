package com.gt.algorithm.tree;

import java.util.*;

/**
 * @author GTsung
 * @date 2022/1/10
 */
public class PrintTree {

    public static void main(String[] args) {
        // 深度遍历即先序遍历
        printBackDeep(createTree());
        // 宽度遍历
    }

    // 前序遍歷: 12###10###7###11###19###16###28###
    private static void printFrontDeep(TreeNode root) {
        if (root == null) return;
        System.out.print(root.value + "###");
        printFrontDeep(root.left);
        printFrontDeep(root.right);
    }

    // 中序遍歷: 7###10###11###12###16###19###28###
    private static void printMiddleDeep(TreeNode root) {
        if (root == null) return;
        printMiddleDeep(root.left);
        System.out.print(root.value + "###");
        printMiddleDeep(root.right);
    }

    // 後序遍歷: 7###11###10###16###28###19###12###
    private static void printBackDeep(TreeNode root) {
        if (root == null) return;
        printBackDeep(root.left);
        printBackDeep(root.right);
        System.out.print(root.value + "###");
    }

    /**
     * 12
     * 10         19
     * 7   11    16   28
     *
     * @return
     */
    private static TreeNode createTree() {
        TreeNode root = new TreeNode(12);
        TreeNode leftChild01 = new TreeNode(10);
        TreeNode rightChild01 = new TreeNode(19);
        TreeNode leftLeftChild0101 = new TreeNode(7);
        TreeNode leftRightChild0102 = new TreeNode(11);
        TreeNode rightLeftChild0101 = new TreeNode(16);
        TreeNode rightRightChild0102 = new TreeNode(28);

        root.left = leftChild01;
        root.right = rightChild01;

        leftChild01.left = leftLeftChild0101;
        leftChild01.right = leftRightChild0102;

        rightChild01.left = rightLeftChild0101;
        rightChild01.right = rightRightChild0102;

        return root;
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

    /**
     * 最大宽度
     *
     * @param root
     * @return
     */
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


    /**
     * 判断是否是二叉搜索树(左子树小于父节点，右子树大于父节点)
     * 中序遍历: 左父右顺序遍历，如果是升序则表示是二叉搜索树
     */
    private static int preValue = Integer.MIN_VALUE;

    private static boolean isBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean isLeftBST = isBST(root.left);
        if (!isLeftBST) {
            return false;
        }
        // 左中右打印值，上一个值为当前值的左子树，因此应该为preValue < curValue
        if (root.value < preValue) {
            return false;
        }
        preValue = root.value;
        return isBST(root.right);
    }
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * 递归求解是否为二叉搜索树
     */
    static class BSTReturn {
        boolean isBST;
        int max;
        int min;

        public BSTReturn(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    private static BSTReturn bstProcess(TreeNode root) {
        if (root == null) return null;
        BSTReturn leftReturn = bstProcess(root.left);
        BSTReturn rightReturn = bstProcess(root.right);

        int min = root.value;
        int max = root.value;
        if (leftReturn != null) {
            min = Math.min(min, leftReturn.min);
            max = Math.max(max, leftReturn.max);
        }
        if (rightReturn != null) {
            min = Math.min(min, rightReturn.min);
            max = Math.max(max, rightReturn.max);
        }
        boolean isBST = true;
        if (leftReturn != null && (!leftReturn.isBST || leftReturn.max > root.value)) {
            isBST = false;
        }
        if (rightReturn != null && (!rightReturn.isBST || rightReturn.min < root.value)) {
            isBST = false;
        }
        return new BSTReturn(isBST, max, min);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 判断是否是完全二叉树
     * 宽度遍历: 如果只有右孩子无左孩子则false; 如果当前节点是一个左右节点不双全的节点，那么之后的其他节点均为子节点
     *
     * @param root
     * @return
     */
    private static boolean isCBT(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        // 是否遇到左右子节点不双全的节点
        boolean leaf = false;
        TreeNode left = null;
        TreeNode right = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            left = root.left;
            right = root.right;

            // 如果遇到了一个左右子节点不双全的节点之后，其他节点不为叶子节点则false
            // 或者当前节点有右无左则false
            if ((leaf && (left != null || right != null))
                    || (left == null && right != null)) {
                return false;
            }

            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
            // 遇到了左右不双全
            if (left == null || right == null) {
                leaf = true;
            }
        }
        return true;
    }
/////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 判断是否是满二叉树
     */
    static class FullReturn {
        int height;
        int nodes;

        public FullReturn(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    private static FullReturn fullProcess(TreeNode root) {
        if (root == null) return new FullReturn(0, 0);
        FullReturn leftReturn = fullProcess(root.left);
        FullReturn rightReturn = fullProcess(root.right);
        int height = Math.max(leftReturn.height, rightReturn.height) + 1;
        int nodes = leftReturn.nodes + rightReturn.nodes + 1;
        return new FullReturn(height, nodes);
    }

    private static boolean isFull(TreeNode root) {
        FullReturn fullReturn = fullProcess(root);
        return fullReturn.nodes == Math.pow(2, fullReturn.height) - 1;
    }

    /////////////////////////////////////////////////////////////////////////////////////

    /**
     * 判断是否为平衡二叉树
     */
    static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    private static ReturnType process(TreeNode root) {
        if (root == null) {
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }

}
