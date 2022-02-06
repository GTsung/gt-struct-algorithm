package com.gt.base.reflect;

import lombok.NonNull;

/**
 * @author GTsung
 * @date 2022/2/7
 */
@MyAnnotation(value = "fuck")
public class TheClass {

    @MyAnnotation(value = "YaLi")
    public String fuckWho;

    @MyAnnotation(value = "cooking")
    public void fuckingDoSomething() {}

    public void fucking(@MyAnnotation(value = "sex") String sex, @NonNull int girlNum) {
        System.out.println(sex);
    }
}
