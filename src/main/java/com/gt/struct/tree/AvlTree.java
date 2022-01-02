package com.gt.struct.tree;

/**
 * AvlTree是左右子节点高度差不能超过1的二叉搜索树
 *
 * @author GTsung
 * @date 2021/12/19
 */
public class AvlTree<T extends Comparable<? super T>> {

    private static final int ALLOWED_IMBALANCE = 1;

    static class AvlNode<T extends Comparable<? super T>> {
        T value;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;

        public AvlNode(T value) {
            this(value, null, null);
        }

        public AvlNode(T value, AvlNode<T> left, AvlNode<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    private AvlNode<T> root;

    private AvlNode<T> findMin(AvlNode<T> node) {
        if (node != null) {
            while (node.left != null) {
                node = node.left;
            }
        }
        return node;
    }

    private int height(AvlNode<T> node) {
        return node == null ? -1 : node.height;
    }

    public void insert(T val) {
        root = insert(root, val);
    }

    private AvlNode<T> insert(AvlNode<T> node, T val) {
        if (node == null) {
            return new AvlNode<>(val);
        }
        int compareVal = node.value.compareTo(val);
        if (compareVal < 0) {
            node.right = insert(node.right, val);
        } else if (compareVal > 0) {
            node.left = insert(node.left, val);
        } else {
        }
        return balance(node);
    }

    private AvlNode<T> balance(AvlNode<T> node) {
        if (node == null) {
            return node;
        }
        if (height(node.left) - height(node.right) > ALLOWED_IMBALANCE) {
            if (height(node.left.left) >= height(node.left.right)) {
                node = rotateWithLeftChild(node);
            } else {
                node = doubleWithLeftChild(node);
            }
        } else if (height(node.right) - height(node.left) > ALLOWED_IMBALANCE) {
            if (height(node.right.right) >= height(node.right.left)) {
                node = rotateWithRightChild(node);
            } else {
                node = doubleWithRightChild(node);
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        // k1.right < k2 ; k1 up ,k2 down ;so k2.left = k1.right
        k2.left = k1.right;
        // k1 < k2,k1 up and k2 down ;so k1.right = k2
        k1.right = k2;
        // calculate height
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    public void remove(T val) {
        root = remove(root, val);
    }

    private AvlNode<T> remove(AvlNode<T> node, T val) {
        if (node == null) {
            return node;
        }
        int compareVal = node.value.compareTo(val);
        if (compareVal < 0) {
            node.left = remove(node.left, val);
        } else if (compareVal > 0) {
            node.right = remove(node.right, val);
        } else if (node.left != null && node.right != null){
            node.value = findMin(node.right).value;
            node.right = remove(node.right, node.value);
        } else {
            node = node.left == null ? node.right : node.left;
        }
        return balance(node);
    }
}
