package com.gt.base.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author GTsung
 * @date 2022/2/7
 */
public class AnnotationReflect {

    public static void main(String[] args) throws Exception {
        Class<TheClass> theClassClass = TheClass.class;

        Annotation[] annotations = theClassClass.getAnnotations();

        Annotation[] declaredAnnotations = theClassClass.getDeclaredAnnotations();

        // class annotation
        Annotation annotation = theClassClass.getAnnotation(MyAnnotation.class);
        if (annotation instanceof MyAnnotation) {
            System.out.println(((MyAnnotation) annotation).value());
        }

        Annotation[] annotationsByType = theClassClass.getAnnotationsByType(MyAnnotation.class);
        for (Annotation a : annotationsByType) {
            if (a instanceof MyAnnotation) {
                System.out.println(((MyAnnotation) a).value());
            }
        }

        // method annotation
        Method fuckingDoSomething = theClassClass.getMethod("fuckingDoSomething");
        Annotation[] fuckingAnnotations = fuckingDoSomething.getDeclaredAnnotations();
        for (Annotation a : fuckingAnnotations) {
            if (a instanceof MyAnnotation) {
                MyAnnotation myAnnotation = (MyAnnotation) a;
                System.out.println(myAnnotation.value());
            }
        }

        // or
        MyAnnotation annotation1 = fuckingDoSomething.getAnnotation(MyAnnotation.class);
        if (annotation1 instanceof MyAnnotation) {
            System.out.println(annotation1.value());
        }

        // parameter annotation
        Method fucking = theClassClass.getMethod("fucking", String.class, int.class);

        // 二維數組, parameterAnnotations.length為方法的參數的個數
        Annotation[][] parameterAnnotations = fucking.getParameterAnnotations();

        Class<?>[] parameterTypes = fucking.getParameterTypes();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Class parameterType = parameterTypes[i];
            // 每個參數的注解遍歷
            for (Annotation a : parameterAnnotations[i]) {
                if (a instanceof MyAnnotation) {
                    System.out.println("parameterType:" + parameterType);
                    System.out.println("value:" + ((MyAnnotation) a).value());
                }
            }
        }

        // field
        Field field = theClassClass.getField("fuckWho");
        Annotation annotation2 = field.getAnnotation(MyAnnotation.class);
        if (annotation2 instanceof MyAnnotation) {
            System.out.println(((MyAnnotation) annotation2).value());
        }
    }
}
