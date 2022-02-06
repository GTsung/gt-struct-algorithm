package com.gt.base.reflect;

import com.gt.reflect.Dog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author GTsung
 * @date 2022/2/7
 */
public class PrivateReflect {

    public static void main(String[] args) throws Exception {
        Class dogClass = Dog.class;
        Dog dog = new Dog("as", 1, new Date());

        // private field
        Field name = dogClass.getDeclaredField("name");
        name.setAccessible(true);
        Object o = name.get(dog);
        
        // private method
        Method bark = dogClass.getDeclaredMethod("bark");
        bark.setAccessible(true);
        Object invoke = bark.invoke(dog);
    }
}
