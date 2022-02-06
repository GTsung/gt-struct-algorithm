package com.gt.base.reflect;

import java.lang.reflect.Constructor;

/**
 * @author GTsung
 * @date 2022/2/7
 */
public class ConstructReflect {

    public static void main(String[] args) throws Exception {
        Class clazz = String.class;

        Constructor[] constructors = clazz.getConstructors();

        // obtain constructor by parameter
        Constructor constructor = clazz.getConstructor(new Class[]{String.class});

        // get parameterType
        Class[] parameterTypes = constructors[2].getParameterTypes();

        // get a instance
        String str = (String) constructor.newInstance("ss");
    }
}
