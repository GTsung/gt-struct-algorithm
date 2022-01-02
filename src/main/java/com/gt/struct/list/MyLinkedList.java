package com.gt.struct.list;

import main.java.gt.struct.queue.MyDeque;

import java.util.NoSuchElementException;

/**
 * @author GTsung
 * @date 2021/12/15
 */
public class MyLinkedList<E> implements MyDeque<E> {

    transient int size = 0;

    transient Node<E> first;

    transient Node<E> last;

    transient int modCount;

    public MyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean remove(Object obj) {
        if (obj == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.value == null) {
                    unLink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.value.equals(obj)) {
                    unLink(x);
                    return true;
                }
            }
        }
        return false;
    }

    E unLink(Node<E> x) {
        final E element = x.value;
        Node<E> prev = x.prev;
        Node<E> next = x.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.value = null;
        size--;
        modCount++;
        return element;
    }

    public boolean add(E value) {
        linkLast(value);
        return true;
    }

    public void addLast(E value) {
        linkLast(value);
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

    public void addFirst(E value) {
        linkFirst(value);
    }

    public E remove() {
        return removeFirst();
    }

    public E getFirst() {
        Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.value;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    public E removeFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unLinkFirst(f);
    }

    public E removeLast() {
        final Node<E> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return unLinkLast(l);
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    private void linkFirst(E value) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, f, value);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
        modCount++;
    }

    private void linkLast(E value) {
        Node<E> l = last;
        Node<E> cur = new Node<>(l, null, value);
        last = cur;
        if (l == null) {
            first = cur;
        } else {
            l.next = cur;
        }
        size++;
        modCount++;
    }

    private E unLinkFirst(Node<E> f) {
        E element = f.value;
        Node<E> n = f.next;
        first = n;
        f.value = null;
        f.next = null;
        if (n == null) {
            last = null;
        } else {
            n.prev = null;
        }
        size--;
        modCount++;
        return element;
    }

    private E unLinkLast(Node<E> l) {
        E element = l.value;
        Node<E> p = l.prev;
        last = p;
        l.prev = null;
        l.value = null;
        if (p == null) {
            first = null;
        } else {
            p.next = null;
        }
        size--;
        modCount++;
        return element;
    }

    // 栈操作
    public void push(E obj) {
        addFirst(obj);
    }

    public E pop() {
        return removeFirst();
    }

    public E peek() {
        return first == null ? null : first.value;
    }

    // 队列操作
    public boolean offer(E obj) {
        return add(obj);
    }

    public  E element() {
        return getFirst();
    }

    public E poll() {
        Node<E> f = first;
        return f == null ? null : unLinkFirst(f);
    }

    private static class Node<E> {

        Node<E> prev;
        Node<E> next;
        E value;

        Node(Node<E> prev, Node<E> next, E value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }


}
