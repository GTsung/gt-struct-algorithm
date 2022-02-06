package com.gt.base.reflect;

import com.gt.reflect.Dog;

import java.lang.reflect.Method;

/**
 * @author GTsung
 * @date 2022/2/7
 */
public class GetterAndSetter {

    public static void main(String[] args) {
        Class dogClass = Dog.class;
        Method[] declaredMethods = dogClass.getDeclaredMethods();

        // judge setter or getter
        for (Method method : declaredMethods) {
            if (isSetter(method)) System.out.println("setter:" + method);
            if (isGetter(method)) System.out.println("getter:" + method);
        }
    }

    private static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (Void.class.equals(method.getReturnType())) return false;
        return true;
    }

    private static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length != 1) return false;
        return true;
    }


}
