package com.gt.base.reflect.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class MethodTest {

    public void test(Integer sum) {
        System.out.println(sum + "----");
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Method test = MethodTest.class.getDeclaredMethod("test", Integer.class);
        Class<?>[] parameterTypes = test.getParameterTypes();
        Arrays.stream(parameterTypes).forEach(p -> System.out.println(p.getName()));
        Type genericParameterType = test.getGenericParameterTypes()[0];
        System.out.println(genericParameterType.getTypeName());

        Parameter parameter = test.getParameters()[0];
        System.out.println(parameter.getType());
        System.out.println(parameter.getName());
        System.out.println(parameter.getParameterizedType().getTypeName());

        MethodTest methodTest = MethodTest.class.newInstance();
        test.invoke(methodTest, 12);
    }
}
