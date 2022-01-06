package com.gt.struct.heap;

import java.util.NoSuchElementException;

/**
 * 左式堆：用于合并
 * npl(x)表示x节点的零路径长，null节点的零路径长为-1，
 * 一个叶子节点或者只具有一个子节点的节点的零路径长为0
 * 一个节点的零路径长比它的具有最小零路径长的子节点零路径长大 1
 * 对于任一节点，它的左子节点零路径长>=其右子节点零路径长
 *
 * 任一节点的npl = min(left.npl, right.npl) + 1;
 * 因为规定left.npl >= right.npl 则也可以写作right.npl + 1
 *
 * @author GTsung
 * @date 2022/1/6
 */
public class LeftHeap<T extends Comparable<? super T>> {

    private LeftNode<T> root;

    static class LeftNode<T> {
        T element;
        LeftNode<T> left;
        LeftNode<T> right;
        int npl;

        LeftNode(T element) {
            this(element, null, null);
        }

        LeftNode(T element, LeftNode<T> lt, LeftNode<T> rt) {
            this.element = element;
            this.left = lt;
            this.right = rt;
            this.npl = 0;
        }
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public T findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return root.element;
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = root.element;
        root = merge(root.left, root.right);
        return element;
    }

    public void insert(T x) {
        root = merge(new LeftNode<>(x), root);
    }

    public void merge(LeftHeap<T> rhs) {
        if (this == rhs) {
            return;
        }
        root = merge(root, rhs.root);
        // 将rhs置为空，便于GC
        rhs.root = null;
    }

    private LeftNode<T> merge(LeftNode<T> h1, LeftNode<T> h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
        if (h1.element.compareTo(h2.element) < 0) {
            return merge1(h1, h2);
        } else {
            return merge1(h2, h1);
        }
    }

    private LeftNode<T> merge1(LeftNode<T> h1, LeftNode<T> h2) {
        // 左式堆的左子节点的零路径长要不小于右子节点的零路径长，
        // 如果 h1.left == null 成立, 则 h1.right == null 亦成立，因此h1是一个单节点
        if (h1.left == null) {
            h1.left = h2;
        } else {
            // h1的元素值是小于h2元素的值的
            h1.right = merge(h1.right, h2);
            // h1.left.npl要不小于h1.right.npl，因此互换 h1.left 与 h1.right
            if (h1.left.npl < h1.right.npl) {
                swapChildren(h1);
            }
            // h1.npl为其子节点的最小npl+1，因为左子节点的npl不小于右子节点的npl，因此使用右子节点的npl+1
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    private static <T> void swapChildren(LeftNode<T> t) {
        LeftNode<T> tmp = t.left;
        t.left = t.right;
        t.right = tmp;
    }

}
