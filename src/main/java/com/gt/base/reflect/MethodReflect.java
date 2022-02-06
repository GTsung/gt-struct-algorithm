package com.gt.base.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author GTsung
 * @date 2022/2/7
 */
public class MethodReflect {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clazz = String.class;

        Method[] methods = clazz.getMethods();

        Method[] declaredMethods = clazz.getDeclaredMethods();

        Method lengthMethod = clazz.getMethod("length");

        Method charAt = clazz.getMethod("charAt", int.class);

        // parameter type
        Class<?>[] parameterTypes = charAt.getParameterTypes();

        Class<?> returnType = charAt.getReturnType();

        // invoke
        String s = "as";
        Object invoke = charAt.invoke(s, 1);
    }
}
