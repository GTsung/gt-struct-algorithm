package com.gt.struct.heap;

import java.util.NoSuchElementException;

/**
 * 二叉堆底层是一个有序数组
 * 下标为i的元素父元素在i/2处，左节点在2i处，右节点在2i+1处
 * 二叉堆中的任意元素的值都大于其父元素的值
 *
 * @author GTsung
 * @date 2022/1/6
 */
public class BinaryHeap<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;

    private T[] array;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (T[]) new Comparable[capacity + 1];
    }

    public BinaryHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];
        int i = 1;
        for (T element : items) {
            array[i++] = element;
        }
        buildHeap();
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public T findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[1];
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T min = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return min;

    }

    private void percolateDown(int hole) {
        int child;
        T temp = array[hole];
        // 下滤
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0) {
                child++;
            }
            if (array[child].compareTo(temp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = temp;
    }

    // 将索引为0处视为暂存地
    public void insert(T x) {
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }
        // hole为存在元素最大下标的下一个位置
        int hole = ++currentSize;
        // 上滤
        for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    private void enlargeArray(int newSize) {
        T[] old = array;
        array = (T[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++) {
            array[i] = old[i];
        }
    }

}
