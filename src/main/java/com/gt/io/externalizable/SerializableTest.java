package com.gt.io.externalizable;

import java.io.*;

public class SerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Dog dog = new Dog("dog", 12);
        System.out.println(dog);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(dog);

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream oit = new ObjectInputStream(bin);
        Dog d = (Dog) oit.readObject();
        System.out.println(d);
    }
}
