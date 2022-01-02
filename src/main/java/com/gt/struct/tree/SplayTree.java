package com.gt.struct.tree;

import java.util.NoSuchElementException;

/**
 * 伸展树：每次访问节点时，将节点提到树根
 *
 * @author GTsung
 * @date 2021/12/22
 */
public class SplayTree<T extends Comparable<? super T>> {

    static class SplayNode<T> {
        T value;
        SplayNode<T> left;
        SplayNode<T> right;

        public SplayNode(T value) {
            this(value, null, null);
        }

        public SplayNode(T value, SplayNode<T> left, SplayNode<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private SplayNode<T> nullNode;
    private SplayNode<T> root;
    private SplayNode<T> newNode = null;
    private SplayNode<T> header = new SplayNode<>(null);

    public SplayTree() {
        nullNode = new SplayNode<>(null);
        nullNode.left = nullNode.right = nullNode;
        root = nullNode;
    }

    public boolean isEmpty() {
        return root == nullNode;
    }

    public void makeEmpty() {
        root = nullNode;
    }

    public boolean contains(T val) {
        if (isEmpty())
            return false;
        root = splay(val, root);
        return root.value.compareTo(val) == 0;
    }

    public T findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        SplayNode<T> node = root;
        while (node.left != nullNode) {
            node = node.left;
        }
        // splay
        root = splay(node.value, root);
        return node.value;
    }

    public T findMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        SplayNode<T> node = root;
        while (node.right != nullNode) {
            node = node.right;
        }
        root = splay(node.value, root);
        return node.value;
    }

    private SplayNode<T> splay(T val, SplayNode<T> node) {

        SplayNode<T> leftTreeMax, rightTreeMin;

        header.left = header.right = nullNode;
        leftTreeMax = rightTreeMin = header;

        nullNode.value = val;

        for (; ; ) {
            int compareVal = val.compareTo(node.value);
            if (compareVal < 0) {
                if (val.compareTo(node.left.value) < 0) {
                    node = rotateWithLeftChild(node);
                }
                if (node.left == nullNode) {
                    break;
                }
                rightTreeMin.left = node;
                rightTreeMin = node;
                node = node.left;
            } else if (compareVal > 0) {
                if (val.compareTo(node.right.value) > 0) {
                    node = rotateWithRightChild(node);
                }
                if (node.right == nullNode) {
                    break;
                }
                leftTreeMax.right = node;
                leftTreeMax = node;
                node = node.right;
            } else {
                break;
            }
        }

        leftTreeMax.right = node.left;
        rightTreeMin.left = node.right;
        node.left = header.right;
        node.right = header.left;
        return node;
    }

    private SplayNode<T> rotateWithRightChild(SplayNode<T> k1) {
        SplayNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    private SplayNode<T> rotateWithLeftChild(SplayNode<T> k2) {
        SplayNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }

    public void insert(T val) {
        if (newNode == null) {
            newNode = new SplayNode<>(null);
        }
        newNode.value = val;

        if (root == nullNode) {
            // first insert, root = newNode
            newNode.left = newNode.right = nullNode;
            root = newNode;
        } else {
            root = splay(val, root);
            int compareVal = val.compareTo(root.value);

            if (compareVal < 0) {
                newNode.left = root.left;
                newNode.right = root;
                root.left = nullNode;
                root = newNode;
            } else if (compareVal > 0) {
                newNode.right = root.right;
                newNode.left = root;
                root.right = nullNode;
                root = newNode;
            } else {
                return;
            }
        }
        newNode = null;
    }

    public void remove(T val) {
        if (!contains(val)) {
            return;
        }
        SplayNode<T> newTree;

        if (root.left == nullNode) {
            newTree = root.right;
        } else {
            newTree = root.left;
            newTree = splay(val, newTree);
            newTree.right = root.right;
        }
        root = newTree;
    }


    public static void main(String[] args) {
        SplayTree<Integer> splayTree = new SplayTree<>();
        splayTree.insert(12);
        splayTree.insert(36);
        splayTree.insert(19);
        splayTree.insert(45);
        splayTree.insert(31);
        Integer minVal = splayTree.findMin();
        System.out.println(minVal);
        System.out.println(splayTree.findMax());
        System.out.println(splayTree.contains(11));
    }
}
