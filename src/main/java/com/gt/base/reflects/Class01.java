package com.gt.base.reflects;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author GTsung
 * @date 2022/3/22
 */
public class Class01 {

    public static class School<String> {

    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<School> school = School.class;
        int modifiers = school.getModifiers();
        System.out.println(Modifier.isStatic(modifiers));

        Object[] signers = school.getSigners();
//        for (Object obj: signers) {
//            System.out.println(obj);
//        }
        School school1 = school.newInstance();

        Type type =  school.getGenericSuperclass();
        Class clazz = (Class) ((ParameterizedType)type).getActualTypeArguments()[0];
        System.out.println(clazz.getName());
    }

}
