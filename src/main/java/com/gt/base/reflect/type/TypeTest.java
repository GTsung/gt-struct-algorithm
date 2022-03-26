package com.gt.base.reflect.type;

import java.lang.reflect.*;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class TypeTest {

    public static void main(String[] args) {
        Type type = null;

        // T[] t 泛型数组
        GenericArrayType arrayType = null;

        // 带有泛型的集合
        ParameterizedType parameterizedType = null;

        // 泛型变量 T t
        TypeVariable typeVariable = null;

        // 带有泛型通配符的表达式  ? extends Number
        WildcardType wildcardType = null;
    }
}
