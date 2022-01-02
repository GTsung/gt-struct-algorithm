package com.gt.struct.queue;

public interface MyQueue<E> {

    boolean add(E e);

    boolean offer(E e);

    /**
     * 队列无元素会抛异常
     * @return
     */
    E remove();

    /**
     * 无元素则返回null
     * @return
     */
    E poll();

    /**
     * 无元素抛异常
     * @return
     */
    E element();

    E peek();
}
