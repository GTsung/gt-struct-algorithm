package com.gt.base.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class PropertiesTest {

    public static void main(String[] args) throws Exception {
//        Properties p = loadProperties1();
        Properties p = loadProperties2();
        System.out.println(p.getProperty("serializer.algorithm"));

    }

    private static Properties loadProperties2() throws IOException {
        Properties properties = new Properties();
        InputStream resourceAsStream = PropertiesTest.class.getClassLoader().getResourceAsStream("application.properties");
        properties.load(resourceAsStream);
        return properties;
    }

    private static Properties loadProperties1() throws IOException {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream("D:\\ideaWork\\gt-struct-algorithm\\src\\main\\resources\\application.properties");
        properties.load(inputStream);
        return properties;
    }
}
