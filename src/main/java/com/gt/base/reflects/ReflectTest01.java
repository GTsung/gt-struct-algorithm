package com.gt.base.reflects;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author GTsung
 * @date 2022/3/22
 */
public class ReflectTest01 {

    public static void main(String[] args) {
        Type type = null;

        int[] a = {1,2};
        GenericArrayType arrayType =null;
        ParameterizedType parameterizedType = null;

        Integer[] o = (Integer[]) Array.newInstance(Integer.class, 12);

    }
}
