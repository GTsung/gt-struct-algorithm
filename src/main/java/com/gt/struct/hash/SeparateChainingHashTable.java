package com.gt.struct.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * 分离链接法的哈希表
 *
 * @author GTsung
 * @date 2022/1/9
 */
public class SeparateChainingHashTable<T> {

    private static final int DEFAULT_TABLE_SIZE = 101;

    // Entry数组
    private List<T>[] theLists;

    private int currentSize;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<>();
        }
    }

    private int nextPrime(int n) {
        if (n % 2 == 0)
            n++;
        for (; !isPrime(n); n += 2) ;
        return n;
    }

    private boolean isPrime(int n) {
        if (n == 2 || n == 3) return true;
        if (n == 1 || n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public void insert(T x) {
        List<T> whichList = theLists[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);
            if (++currentSize > theLists.length) {
                rehash();
            }
        }
    }

    private void rehash() {
        List<T>[] oldLists = theLists;
        theLists = new List[nextPrime(2 * theLists.length)];
        for (int j = 0; j < theLists.length; j++) {
            theLists[j] = new LinkedList<>();
        }
        currentSize = 0;
        for (List<T> list : oldLists) {
            for (T item : list) {
                insert(item);
            }
        }
    }

    private int myhash(T x) {
        int hashVal = x.hashCode();
        hashVal %= theLists.length;
        if (hashVal < 0) {
            hashVal += theLists.length;
        }
        return hashVal;
    }

    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }

    public boolean contains(T x) {
        List<T> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    public void remove(T x) {
        List<T> whichList = theLists[myhash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }
}
