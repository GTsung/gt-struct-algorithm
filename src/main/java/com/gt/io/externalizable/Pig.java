package com.gt.io.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Pig implements Externalizable {

    public Pig() {
        System.out.println("默认构造函数");
    }

    public Pig(String name, Integer age) {
        System.out.println("参数构造函数");
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + age;
    }

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 指定属性写入流中
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("调用writeExternal()方法");
        out.writeObject(name);
//        out.writeObject(age);
    }

    /**
     * 读取指定属性到流中
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("调用readExternal()方法");
        name = (String) in.readObject();
//        age = (Integer) in.readObject();
    }
}
