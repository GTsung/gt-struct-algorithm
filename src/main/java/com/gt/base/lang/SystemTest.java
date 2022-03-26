package com.gt.base.lang;

import java.util.Properties;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class SystemTest {

    public static void main(String[] args) {
        // 只加载Java虚拟机的属性及操作系统环境的属性
        Properties properties = System.getProperties();
        properties.stringPropertyNames()
                .forEach(name -> System.out.println(name + ":" + properties.getProperty(name)));
    }
}
