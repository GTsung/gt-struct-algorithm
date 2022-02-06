package com.gt.base.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 泛型反射
 * @author GTsung
 * @date 2022/2/7
 */
public class GenericsReflect {

    public static void main(String[] args) throws Exception {
        // 泛型在編譯期間會被擦除，可以獲取運行時的屬性類型來獲取

        // generic method return types
        Method method = MyClass.class.getMethod("getList");
        // 獲取方法返回的汎型類型
        Type genericReturnType = method.getGenericReturnType();

        if (genericReturnType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericReturnType;
            // 實際的參數類型
            Type[] actualTypeArguments = type.getActualTypeArguments();
            for (Type typeArgument : actualTypeArguments) {
                Class typeArgClass = (Class) typeArgument;
                System.out.println("typeArgClass = " + typeArgClass);
            }
        }

        // generic method parameter types
        Method setList = MyClass.class.getMethod("setList", List.class);
        // 獲取參數汎型
        Type[] genericParameterTypes = setList.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            // instanceof ParameterizedType
            if (genericParameterType instanceof ParameterizedType) {
                ParameterizedType aType = (ParameterizedType) genericParameterType;
                Type[] actualTypeArguments = aType.getActualTypeArguments();
                for (Type parameterArgType : actualTypeArguments) {
                    Class parameterArgTypeClass = (Class) parameterArgType;
                    System.out.println("parameterArgType:" + parameterArgTypeClass);
                }
            }
        }

        // generic field types
        Field map = MyClass.class.getField("map");
        Type genericType = map.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType type = (ParameterizedType) genericType;
            Type[] actualTypeArguments = type.getActualTypeArguments();
            for (Type aType : actualTypeArguments) {
                Class clazz = (Class) aType;
                System.out.println("aType:" + clazz);
            }
        }



    }

}
