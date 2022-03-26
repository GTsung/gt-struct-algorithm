package com.gt.base.reflect.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.List;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class WildcardTypeTest {

    // <? extends Number> 或者 <? super Integer>
    private List<? extends Number> list;

    public static void main(String[] args) throws NoSuchFieldException {
        printWildcardType();
    }

    private static void printWildcardType() throws NoSuchFieldException {
        Type type = WildcardTypeTest.class.getDeclaredField("list").getGenericType();
        System.out.println(type.getTypeName());
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
        WildcardType wildcardType = (WildcardType) actualTypeArgument;
        System.out.println(wildcardType.getTypeName());
        Arrays.stream(wildcardType.getUpperBounds()).forEach(b -> {
            System.out.println(b.getTypeName());
        });
    }
}
