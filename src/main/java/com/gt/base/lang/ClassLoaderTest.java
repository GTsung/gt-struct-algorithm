package com.gt.base.lang;

/**
 * @author GTsung
 * @date 2022/3/24
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        Class<?> aClass = classLoader.loadClass("com.gt.base.lang.ClassLoaderTest");
        ClassLoaderTest o = (ClassLoaderTest) aClass.newInstance();
        o.print();
    }

    private void print() {
        System.out.println("hello");
    }
}
