package com.gt.base.reflect.type;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class TypeVariableTest<T extends Integer> {

    private T t;

    public static void main(String[] args) throws NoSuchFieldException {
        printFieldType();
    }

    private static void printFieldType() throws NoSuchFieldException {
        Type type = TypeVariableTest.class.getDeclaredField("t").getGenericType();
        // T
        System.out.println(type.getTypeName());

        TypeVariable variable = (TypeVariable) type;
        System.out.println(variable.getName());

        // 泛型变量的声名类 class com.gt.base.reflect.type.TypeVariableTest
        System.out.println(variable.getGenericDeclaration());

        Arrays.stream(variable.getBounds()).forEach(a -> {
            // 获取泛型变量的上限 Integer
            System.out.println(a.getTypeName());
        });
    }

}
