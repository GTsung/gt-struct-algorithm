package com.gt.io.externalizable;

import java.io.*;

public class ExternalizableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("序列化之前");
        Pig pig = new Pig("p", 21);
        System.out.println(pig);

        System.out.println("序列化操作, writeObject");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(byteArrayOutputStream);
        os.writeObject(pig);

        System.out.println("反序列化操作,readObject");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream is = new ObjectInputStream(byteArrayInputStream);

        Pig pig1 = (Pig) is.readObject();
        System.out.println(pig1);

    }
}
