package com.gt.struct.queue;

/**
 * @author GTsung
 * @date 2021/12/16
 */
public interface MyDeque<E> extends MyQueue<E> {

    void addFirst(E e);

    void addLast(E e);

    boolean offerFirst(E e);

    boolean offerLast(E e);

    E removeFirst();

    E removeLast();

    E pollFirst();

    E pollLast();

    E getFirst();

    E getLast();

    E peekFirst();

    E peekLast();

    // queue
    boolean add(E e);

    boolean offer(E e);

    E remove();

    E poll();

    E peek();

    E element();

    // stack
    void push(E e);

    E pop();



}
