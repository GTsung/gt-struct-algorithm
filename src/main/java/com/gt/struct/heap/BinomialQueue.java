package com.gt.struct.heap;

import java.util.NoSuchElementException;

/**
 * 二项队列:二项树的森林，高度为k的二项队列一共有k个节点
 * k个节点的二项队列高度为logk
 * 两个单节点可以联合为一个高为1的树，两个高为1的树可以联合为一个高为2的树
 * <p>
 * 12------------10--------------------20
 * /  \                 /  | \
 * 15    50             70  50  40
 * |                  / |    |
 * 30               80  85  65
 * |
 * 100
 * <p>
 * 组成这个二项队列的有高度为0、2、3的三个树
 * 可以利用二进制位表示 00001101 ---> 从二进制位也可以看出没有高度为1的树，且可以计算出节点个数为13
 *
 * @author GTsung
 * @date 2022/1/6
 */
public class BinomialQueue<T extends Comparable<? super T>> {

    static class BinNode<T> {
        T element;
        BinNode<T> leftChild;
        BinNode<T> nextSibling; // right child

        public BinNode(T element, BinNode<T> leftChild, BinNode<T> nextSibling) {
            this.element = element;
            this.leftChild = leftChild;
            this.nextSibling = nextSibling;
        }

        public BinNode(T element) {
            this(element, null, null);
        }
    }

    private static final int DEFAULT_TREES = 1;

    private int currentSize;

    private BinNode<T>[] theTrees;

    public BinomialQueue() {
        theTrees = new BinNode[DEFAULT_TREES];
        makeEmpty();
    }

    public BinomialQueue(T item) {
        currentSize = 1;
        theTrees = new BinNode[1];
        theTrees[0] = new BinNode<>(item, null, null);
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < theTrees.length; i++) {
            theTrees[i] = null;
        }
    }

    public T findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return theTrees[findMinIndex()].element;
    }

    private int findMinIndex() {
        int i;
        int minIndex;
        // first find nonNull element , record index
        for (i = 0; theTrees[i] == null; i++) ;

        // go on finding nonNull element, compare element, update minIndex
        for (minIndex = i; i < theTrees.length; i++) {
            if (theTrees[i] != null
                    && theTrees[i].element.compareTo(theTrees[minIndex].element) < 0)
                minIndex = i;
        }
        return minIndex;
    }

    public void insert(T x) {
        merge(new BinomialQueue<>(x));
    }

    public void merge(BinomialQueue<T> rhs) {
        if (this == rhs) return;
        currentSize += rhs.currentSize;
        if (currentSize > capacity()) {
            int newNumTrees = Math.max(theTrees.length, rhs.theTrees.length) + 1;
            expandTheTrees(newNumTrees);
        }
        BinNode<T> carry = null;
        for (int i = 0, j = 1; j <= currentSize; i++, j *= 2) {
            BinNode<T> t1 = theTrees[i];
            BinNode<T> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch (whichCase) {
                case 0: /* No trees */
                case 1: /* Only this */
                    break;
                case 2: /* Only rhs */
                    theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                case 4: /* Only carry */
                    theTrees[i] = carry;
                    carry = null;
                    break;
                case 3: /* this and rhs */
                    carry = combineTrees(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                case 5: /* this and carry */
                    carry = combineTrees(t1, carry);
                    theTrees[i] = null;
                    break;
                case 6: /* rhs and carry */
                    carry = combineTrees(t2, carry);
                    rhs.theTrees[i] = null;
                    break;
                case 7: /* All three */
                    theTrees[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.theTrees[i] = null;
                    break;
            }
        }

        for (int k = 0; k < rhs.theTrees.length; k++)
            rhs.theTrees[k] = null;
        rhs.currentSize = 0;

    }

    private BinNode<T> combineTrees(BinNode<T> t1, BinNode<T> t2) {
        if (t1.element.compareTo(t2.element) > 0)
            return combineTrees(t2, t1);
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    private void expandTheTrees(int newNumTrees) {
        BinNode<T>[] old = theTrees;
        int oldNumTrees = theTrees.length;
        theTrees = new BinNode[newNumTrees];
        for (int i = 0; i < Math.min(oldNumTrees, newNumTrees); i++) {
            theTrees[i] = old[i];
        }
        for (int i = oldNumTrees; i < newNumTrees; i++) {
            theTrees[i] = null;
        }
    }

    private int capacity() {
        return (1 << theTrees.length) - 1;
    }


}
