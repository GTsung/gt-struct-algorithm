package com.gt.base.reflect.type;

import java.util.Arrays;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class ClassTest<Integer> {

    public void test() {}

    public static void main(String[] args) {
//        printComponentType();

//        printSigners();
    }

    public static void printSigners() {
        Object[] signers = ClassTest.class.getSigners();
        Arrays.stream(signers).forEach(s -> System.out.println(s.getClass().getName()));
    }

    private static void printComponentType() {
        // [Lcom.gt.base.reflect.type.ClassTest;
        System.out.println(ClassTest[].class.getName());
        Class<?> componentType = ClassTest[].class.getComponentType();
        System.out.println(componentType.getName());
    }
}
