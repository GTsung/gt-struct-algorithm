package com.gt.pattern.struct.flyweight;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class Client {

    // 享元模式一般使用在大量相同对象创建的场景，可以使相同的对象分为相同状态与不同状态
    // 将相同状态的享元对象池化，不同状态后续设置
    public static void main(String[] args) {
        BoxFactory boxFactory = BoxFactory.getInstance();
        AbstractBox lBox = boxFactory.getBoxByShape("L");
        lBox.display("red");

        AbstractBox lBox2 = boxFactory.getBoxByShape("L");
        lBox2.display("green");

        System.out.println(lBox == lBox2);
    }
}
