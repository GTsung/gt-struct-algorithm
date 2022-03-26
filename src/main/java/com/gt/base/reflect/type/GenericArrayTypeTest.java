package com.gt.base.reflect.type;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class GenericArrayTypeTest<T> {

    private T[] t;

    public static void main(String[] args) throws NoSuchFieldException {
        Type type = GenericArrayTypeTest.class.getDeclaredField("t").getGenericType();
        // T[]
        System.out.println(type.getTypeName());

        GenericArrayType genericArrayType = (GenericArrayType) type;
        // T[]
        System.out.println(genericArrayType.getTypeName());

        Type genericComponentType = genericArrayType.getGenericComponentType();
        // T 获取数组元素泛型类
        System.out.println(genericComponentType.getTypeName());
    }
}
