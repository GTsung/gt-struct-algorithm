package com.gt.struct.list;


import java.util.Arrays;

/**
 * @author GTsung
 * @date 2021/12/15
 */
public class MyArrayList<E> {

    private Object[] arr;

    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    private int modCount = 0;

    private static final int MAX_VALUE = Integer.MAX_VALUE;

    public MyArrayList() {
        this(new Object[DEFAULT_CAPACITY]);
    }

    public MyArrayList(int size) {
        this(new Object[size]);
    }

    public MyArrayList(Object[] arr) {
        this.arr = arr;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean add(E num) {
        // 扩容
        ensureCapacity(size + 1);
        arr[size++] = num;
        return true;
    }

    private void ensureCapacity(int newSize) {
        if (newSize > arr.length) {
            grow(newSize * 2);
        }
    }

    private void grow(int newSize) {
        modCount++;
        if (newSize - MAX_VALUE > 0) {
            newSize = MAX_VALUE;
        }
        arr = Arrays.copyOf(arr, newSize);
    }

    public void add(E element, int index) {
        checkForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = element;
        size++;
    }

    private void checkForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    public E get(int index) {
        checkBound(index);
        return (E) arr[index];
    }

    public E set(E element, int index) {
        checkBound(index);
        E oldValue = (E) arr[index];
        arr[index] = element;
        return oldValue;
    }

    private void checkBound(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public E remove(int index) {
        checkBound(index);
        E oldValue = (E) arr[index];
        modCount++;
        int numMoved = size - index - 1; // 移动的元素个数
        if (numMoved > 0) {
            System.arraycopy(arr, index + 1, arr, index, numMoved);
        }
        arr[size--] = null;
        return oldValue;
    }

    public int indexOf(E obj) {
        if (obj == null) {
            for (int i = 0; i < size; i++) {
                if (arr[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (arr[i].equals(obj)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(E obj) {
        if (obj == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (arr[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (arr[i].equals(obj)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contains(E obj) {
        return indexOf(obj) != -1;
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(12);
        list.add(33);
        System.out.println(list.contains(12));
        System.out.println(list.indexOf(33));
        list.remove(0);
    }
}
