package com.gt.threads.nolock;

import java.util.concurrent.atomic.*;

/**
 * @author GTsung
 * @date 2022/2/13
 */
public class AtomicTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.incrementAndGet());

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        AtomicReference<String> str = new AtomicReference<>("s");

        // ABA問題
        // pair封裝了對象和版本號
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(12, 1);

        Atom atom = new Atom(12, "atom");
        // Atomic變更屬性，一定要用volatile修飾 且類型為int
        AtomicIntegerFieldUpdater<Atom> age = AtomicIntegerFieldUpdater.newUpdater(Atom.class, "age");
        age.getAndIncrement(atom);
        System.out.println(atom.age);


    }

    static class Atom {
        volatile int age;
        String name;

        public Atom(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public Atom() {}
    }
}
