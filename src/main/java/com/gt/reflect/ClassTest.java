package com.gt.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author GTsung
 * @date 2022/1/16
 */
@Slf4j
public class ClassTest {

    public static void main(String[] args) throws Exception {
        create();
    }

    private static void create() throws Exception {
        log.info("根據類名創建Class對象: {}", Dog.class);
        log.info("根據對象創建: " + new Dog().getClass());
        log.info("根據全限定名: " + Class.forName("com.gt.reflect.Dog"));
        log.info("全限定名: " + Dog.class.getName());
        log.info("類名: " + Dog.class.getSimpleName());
        log.info("實例化: " + Dog.class.newInstance());

        //
        Dog dog = new Dog();
        // true
        log.info("dOG是否能表示為Animal" + Animal.class.isInstance(dog));

        // 獲取實現的接口
        Class<?>[] interfaces = Dog.class.getInterfaces();
        //interface com.gt.reflect.Animal
        Arrays.stream(interfaces).forEach(System.out::println);

        // 獲取直接父類 class com.gt.reflect.DogFather
        System.out.println(dog.getClass().getSuperclass());

        // 獲取公共屬性getFields(), getDeclaredFields()
//        Field[] fields = Dog.class.getFields();
        Field[] fields = Dog.class.getDeclaredFields();
        Arrays.stream(fields).forEach(f->{
            System.out.println(f.getName());
        });

        Method[] methods = Dog.class.getMethods();
        Arrays.stream(methods).forEach(m-> System.out.println(m.getReturnType().getName()));

        Method method = methods[0];
        // boolean
        System.out.println(method.getReturnType());
        System.out.println(method.getName());
        // boolean
        System.out.println(method.getAnnotatedReturnType().getType());

        Arrays.stream(method.getParameterTypes()).forEach(p-> System.out.println(p.getName()));

        Class[] parameterizedTypes = method.getParameterTypes();

        Method m = Dog.class.getMethod("drink", String.class);
        m.invoke(Dog.class.newInstance(), "ss");

        
    }

}
