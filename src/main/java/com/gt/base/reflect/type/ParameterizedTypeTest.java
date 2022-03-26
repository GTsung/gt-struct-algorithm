package com.gt.base.reflect.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class ParameterizedTypeTest<T, K> {

    private List<T> list;

    private Map<T, K> map;

    public static void main(String[] args) throws NoSuchFieldException {
//        printListField();
        printMapField();
    }

    private static void printMapField() throws NoSuchFieldException {
        Type mapType = ParameterizedTypeTest.class.getDeclaredField("map").getGenericType();
        // java.util.Map<T, K>
        System.out.println(mapType.getTypeName());

        ParameterizedType parameterizedType = (ParameterizedType) mapType;
        Arrays.stream(parameterizedType.getActualTypeArguments()).forEach(type -> {
            // T
            // K
            System.out.println(type.getTypeName());
        });
        // interface java.util.Map
        System.out.println(parameterizedType.getRawType());
    }

    private static void printListField() throws NoSuchFieldException {
        Type fieldType = ParameterizedTypeTest.class.getDeclaredField("list")
                .getGenericType();
        // java.util.List<T>
        System.out.println(fieldType.getTypeName());

        ParameterizedType parameterizedType = (ParameterizedType) fieldType;
        System.out.println(parameterizedType);
        // [T]
        System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));
        // T
        System.out.println(parameterizedType.getActualTypeArguments()[0]);

        // interface java.util.List 表示声名此泛型的集合类
        System.out.println(parameterizedType.getRawType());
    }

}
