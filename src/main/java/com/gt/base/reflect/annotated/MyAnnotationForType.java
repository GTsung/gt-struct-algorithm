package com.gt.base.reflect.annotated;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE_USE,
        ElementType.TYPE, ElementType.PARAMETER})
public @interface MyAnnotationForType {

    int value();
}
