package com.gt.base.reflect.annotated;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author GTsung
 * @date 2022/3/23
 */
public class AnnotatedParameterizedTypeTest {

    private List<@MyAnnotationForType(1) String> list1;

    private List<@MyAnnotationForType(2) List<@MyAnnotationForType(3) String>> list2;

    public static void main(String[] args) {
//        printFieldAnnotatedParameterizedType();
        printAnnotatedType();
    }

    private static void printAnnotatedType() {
        Field[] declaredFields = AnnotatedParameterizedTypeTest.class.getDeclaredFields();
//        Arrays.stream(declaredFields)
                // java.util.List<java.lang.String>
                // java.util.List<java.util.List<java.lang.String>>
//                .forEach(field -> System.out.println(field.getAnnotatedType().getType().getTypeName()));

        for (Field field : declaredFields) {
            AnnotatedType annotatedType = field.getAnnotatedType();
            System.out.println(annotatedType);
            AnnotatedParameterizedType parameterizedType = (AnnotatedParameterizedType) annotatedType;
            AnnotatedType[] annotatedActualTypeArguments = parameterizedType.getAnnotatedActualTypeArguments();
            Arrays.stream(annotatedActualTypeArguments).forEach(a -> {
                Annotation[] declaredAnnotations = a.getDeclaredAnnotations();
                for (Annotation ann: declaredAnnotations) {
                    // @com.gt.base.reflect.annotated.MyAnnotationForType(value=1)
                    // @com.gt.base.reflect.annotated.MyAnnotationForType(value=2)
                    System.out.println(ann);
                }
            });
        }
    }

    private static void printFieldAnnotatedParameterizedType() {
        Arrays.stream(AnnotatedParameterizedTypeTest.class.getDeclaredFields())
                .forEach(field -> print((AnnotatedParameterizedType) field.getAnnotatedType()));
    }

    private static void print(AnnotatedParameterizedType annotatedType) {
        Arrays.stream(annotatedType.getAnnotatedActualTypeArguments())
                .forEach(type -> {
                    System.out.println(Arrays.toString(type.getDeclaredAnnotations()));
                    if (type instanceof AnnotatedParameterizedType) {
                        print((AnnotatedParameterizedType) type);
                    }
                });
    }
}
