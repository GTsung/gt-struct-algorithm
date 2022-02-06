package com.gt.base.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author GTsung
 * @date 2022/2/7
 */
public class ClazzReflect {

    public static void main(String[] args) throws Exception {
        // obtain class object
        Class clazz = String.class;
        clazz = Class.forName("java.lang.String");
        String str = "";
        clazz = str.getClass();

        // obtain class name
        String name = clazz.getName(); // full name
        name = clazz.getSimpleName(); // only class name

        // obtain modifier
        int modifiers = clazz.getModifiers();
        boolean anAbstract = Modifier.isAbstract(modifiers);
        boolean aFinal = Modifier.isFinal(modifiers);
        boolean anInterface = Modifier.isInterface(modifiers);
        boolean aPrivate = Modifier.isPrivate(modifiers);
        boolean aStatic = Modifier.isStatic(modifiers);
        boolean aSynchronized = Modifier.isSynchronized(modifiers);

        // package info
        Package aPackage = clazz.getPackage();

        // superClass
        Class superclass = clazz.getSuperclass();

        // interface, only return directly implements interface
        Class[] interfaces = clazz.getInterfaces();

        // constructor
        Constructor[] constructors = clazz.getConstructors();

        // method
        Method[] methods = clazz.getMethods();

        Field[] fields = clazz.getFields();

        Annotation[] annotations = clazz.getAnnotations();

    }
}
