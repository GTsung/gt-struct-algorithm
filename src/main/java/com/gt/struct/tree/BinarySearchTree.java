package com.gt.struct.tree;

import java.util.NoSuchElementException;

/**
 * 二叉树：
 * 完全二叉树(最后一层的节点可能未满，且未满层缺少的是右节点)
 * 全满二叉树(每层的节点均满)，全满二叉树属于完全二叉树，反之则不一定
 * <p>
 * 二叉搜索树：
 * 左侧子节点小于节点，右侧子节点大于节点的二叉树
 *
 * @author GTsung
 * @date 2021/12/19
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    /**
     * 二叉树节点
     *
     * @param <T>
     */
    static class BinaryNode<T> {
        T value;
        BinaryNode left;
        BinaryNode right;

        BinaryNode(T value) {
            this(value, null, null);
        }

        BinaryNode(T value, BinaryNode left, BinaryNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 根节点
     */
    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    /**
     * 置为空
     */
    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 是否包含
     *
     * @param val
     * @return
     */
    public boolean contains(T val) {
        return contains(root, val);
    }

    private boolean contains(BinaryNode<T> node, T val) {
        if (node == null) return false;
        if (node.value.compareTo(val) > 0) {
            return contains(node.left, val);
        } else if (node.value.compareTo(val) < 0) {
            return contains(node.right, val);
        } else {
            return true;
        }
    }

    /**
     * 找最小值
     *
     * @return
     */
    public T findMin() {
        if (isEmpty()) throw new NoSuchElementException();
        return findMin(root).value;
    }

    private BinaryNode<T> findMin(BinaryNode<T> node) {
        if (node == null) return null;
        if (node.left == null) return node;
        return findMin(node.left);
    }

    /**
     * 找最大值
     *
     * @return
     */
    public T findMax() {
        if (isEmpty()) throw new NoSuchElementException();
        return findMax(root).value;
    }

    private BinaryNode<T> findMax(BinaryNode<T> node) {
        if (node != null) {
            while (node.right != null) {
                node = node.right;
            }
        }
        return node;
    }

    /**
     * 遍历
     */
    public void printBefore(BinaryNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + "  ");
        printBefore(node.left);
        printBefore(node.right);
    }

    public void printMiddle(BinaryNode<T> node) {
        if (node == null) {
            return;
        }
        printMiddle(node.left);
        System.out.print(node.value + "  ");
        printMiddle(node.right);
    }

    public void printAfter(BinaryNode<T> node) {
        if (node == null) {
            return;
        }
        printAfter(node.left);
        printAfter(node.right);
        System.out.print(node.value + "  ");
    }

    /**
     * 新增
     *
     * @param val
     */
    public void insert(T val) {
        root = insert(root, val);
    }

    private BinaryNode<T> insert(BinaryNode<T> node, T val) {
        if (node == null) {
            return new BinaryNode<>(val);
        }
        int compareVal = node.value.compareTo(val);
        if (compareVal < 0) {
            node.right = insert(node.right, val);
        } else if (compareVal > 0) {
            node.left = insert(node.left, val);
        } else {}
        return node;
    }

    /**
     * 移除
     *
     * @param val
     */
    public void remove(T val) {
        root = remove(root, val);
    }

    private BinaryNode<T> remove(BinaryNode<T> node, T val) {
        if (node == null) return null;
        int compareVal = node.value.compareTo(val);
        if (compareVal < 0) {
            node.right = remove(node.right, val);
        } else if (compareVal > 0) {
            node.left = remove(node.left, val);
        } else if (node.left != null && node.right != null) {
            // two children, find right child's min value , delete right child's min node
            node.value = (T) findMin(node.right).value;
            node.right = remove(node.right, node.value);
        } else {
            // one child or null child
            node = node.left == null ? node.right : node.left;
        }
        return node;
    }

    public void print(BinaryNode<T> node , T val, int direction) {
        if (node == null) return;
        if (direction == 0) System.out.printf("%2d is root\n", node.value);
        else System.out.printf("%2d is %2d's %6s child\n", node.value, val, direction == 1 ? "right" : "left");
        print(node.left, node.value, -1);
        print(node.right, node.value, 1);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bsTree = new BinarySearchTree<>();
        bsTree.insert(3);
        bsTree.insert(5);
        bsTree.insert(4);
        bsTree.insert(1);
        bsTree.insert(2);
        bsTree.insert(6);
        bsTree.printBefore(bsTree.root);
        System.out.println();
        bsTree.printMiddle(bsTree.root);
        System.out.println();
        bsTree.printAfter(bsTree.root);
        System.out.println();
        bsTree.print(bsTree.root, bsTree.root.value, 0);

        System.out.println(bsTree.contains(2));
        System.out.println(bsTree.findMax());
        System.out.println(bsTree.findMin());
        bsTree.remove(6);
        System.out.println(bsTree.findMax());
    }
}
