package com.gt.base.reflect;

import java.lang.reflect.Field;

/**
 * @author GTsung
 * @date 2022/2/7
 */
public class FieldReflect {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = String.class;

        // only public
        Field[] fields = clazz.getFields();

        // full
        Field[] declaredFields = clazz.getDeclaredFields();

        Field hash = clazz.getDeclaredField("hash");

        // name
        String name = hash.getName();
        Class<?> type = hash.getType();

        String s = "as";

        // get
        Object o = hash.get(s);
        hash.setAccessible(true);

        // set
        hash.set(s, o);
    }
}
