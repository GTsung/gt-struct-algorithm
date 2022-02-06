package com.gt.base.reflect;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author GTsung
 * @date 2022/2/7
 */
public class ArrayReflect {

    public static void main(String[] args) throws Exception {
        // create Arrays
        int[] intArray = (int[]) Array.newInstance(int.class, 3);

        // set and get
        Array.set(intArray, 0, 1);
        Array.set(intArray, 1, 10);
        Array.set(intArray, 2, 100);
        Arrays.stream(intArray).forEach(System.out::println);

        System.err.println("------------");

        int element = (int) Array.get(intArray, 1);
        System.out.println(element);

        Class aClass = String[].class;
        Class aClass1 = int[].class;
        System.out.println(aClass);
        System.out.println(aClass1);

        // obtain int[] Class
        Class intArrayClass = Class.forName("[I");
        System.out.println(intArrayClass.getName());

        // obtain object[] Class
        Class<?> strArrayClass = Class.forName("[Ljava.lang.String;");

        String theClassName = "int";
        Class theClass = getClass(theClassName);
        // 獲取數組類
        Class<?> arrayClass = Array.newInstance(theClass, 0).getClass();
        System.out.println(arrayClass.getName()); // [I ---> intArray

        // obtain the component type of an Array
        String[] strings = new String[3];
        Class stringArrayClass = strings.getClass();
        Class componentType = stringArrayClass.getComponentType();
        System.out.println(componentType);
    }

    private static Class getClass(String className) throws ClassNotFoundException {
        if ("int".equals(className)) return int.class;
        if ("long".equals(className)) return long.class;
        return Class.forName(className);
    }

}
