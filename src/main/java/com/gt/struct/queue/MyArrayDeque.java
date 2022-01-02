package com.gt.struct.queue;

import java.util.NoSuchElementException;

/**
 * 循环队列(双端队列) 也可作栈、队列使用
 * @author GTsung
 * @date 2021/12/16
 */
public class MyArrayDeque<E> implements MyDeque<E> {

    transient Object[] elements;

    // 指向第一个元素的下标
    transient int head;

    // 指向最后一个元素下一个下标(这个下标上无元素)
    transient int tail;

    private static final int MIN_INITIAL_CAPACITY = 8;

    public MyArrayDeque() {
        elements = new Object[16];
    }

    public MyArrayDeque(int numElements) {
        elements = new Object[calculateNum(numElements)];
    }

    private int calculateNum(int numElements) {
        int initCapacity = MIN_INITIAL_CAPACITY;
        if (numElements >= initCapacity) {
            initCapacity = numElements;
            initCapacity |= (initCapacity >>> 1);
            initCapacity |= (initCapacity >>> 2);
            initCapacity |= (initCapacity >>> 4);
            initCapacity |= (initCapacity >>> 8);
            initCapacity |= (initCapacity >>> 16);
            initCapacity++;
            if (initCapacity < 0) {
                initCapacity >>>= 1;
            }
        }
        return initCapacity;
    }


    @Override
    public void addFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        elements[head = (head - 1) & (elements.length - 1)] = e;
        if (head == tail) {
            // 扩容
            doubleCapacity();
        }
    }

    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p;
        int newLength = elements.length << 1;
        if (newLength < 0) {
            throw new IllegalStateException("deque is full");
        }
        Object[] a = new Object[newLength];
        System.arraycopy(elements, p, a, 0, r);
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }

    @Override
    public void addLast(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        elements[tail] = e;
        if ((tail = (tail + 1) & (elements.length - 1)) == head) {
            doubleCapacity();
        }
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        E e = pollFirst();
        if (e == null) {
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    public E removeLast() {
        E e = pollLast();
        if (e == null) {
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    public E pollFirst() {
        int h = head;
        @SuppressWarnings("unchecked")
        E e = (E) elements[h];
        if (e == null) {
            return null;
        }
        elements[head] = null;
        head = (head + 1) & (elements.length - 1);
        return e;
    }

    @Override
    public E pollLast() {
        int t = (tail - 1) & (elements.length - 1);
        @SuppressWarnings("unchecked")
        E e = (E) elements[t];
        if (e == null) {
            return null;
        }
        elements[t] = null;
        tail = t;
        return e;
    }

    @Override
    public E getFirst() {
        @SuppressWarnings("unchecked")
        E e = (E) elements[head];
        if (e == null) {
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    public E getLast() {
        @SuppressWarnings("unchecked")
        E e = (E) elements[(tail - 1) & (elements.length - 1)];
        if (e == null) {
            throw new NoSuchElementException();
        }
        return e;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peekFirst() {
        return (E) elements[head];
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peekLast() {
        return (E) elements[(tail - 1) & (elements.length - 1)];
    }

    // queue
    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        return offerLast(e);
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    public static void main(String[] args) {
        MyArrayDeque<Integer> arrayDeque = new MyArrayDeque<>();
        arrayDeque.offer(12);
        arrayDeque.poll();
        arrayDeque.push(11);
        arrayDeque.pop();

        arrayDeque.addFirst(1);
        arrayDeque.peekFirst();

    }

    public int size() {
        return (tail - head) & (elements.length -1);
    }

    public boolean isEmpty() {
        return head == tail;
    }
}
