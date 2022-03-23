package com.gt.base.reflect.annotated;

import java.lang.reflect.*;

/**
 * @author GTsung
 * @date 2022/3/23
 */
public class AnnotatedTypeTest {

    public static void main(String[] args) {
        // AnnotatedType是Type的加强类型
        AnnotatedType annotatedType = null;
        // 被注解的参数化类型
        AnnotatedParameterizedType annotatedParameterizedType = null;
        // 被注解的类型变量
        AnnotatedTypeVariable annotatedTypeVariable = null;
        // 被注解的通配符类型
        AnnotatedWildcardType annotatedWildcardType = null;
        // 被注解的数组类型
        AnnotatedArrayType annotatedArrayType = null;
    }
}
