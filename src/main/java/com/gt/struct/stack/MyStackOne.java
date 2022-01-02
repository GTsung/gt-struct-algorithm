package com.gt.struct.stack;


import com.gt.struct.list.MyArrayList;

/**
 * @author GTsung
 * @date 2021/12/15
 */
public class MyStackOne<E> {

    private MyArrayList<E> list;

    public MyStackOne() {
        this.list = new MyArrayList<>();
    }

    public boolean push(E obj) {
        list.add(obj);
        return true;
    }

    public E pop() {
        return list.remove(list.size() -1);
    }

    public E peek() {
        return list.get(list.size() -1);
    }
}
