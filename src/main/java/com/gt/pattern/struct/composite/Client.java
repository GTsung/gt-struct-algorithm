package com.gt.pattern.struct.composite;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class Client {
    public static void main(String[] args) {
        Component component1 = new Menu("America", 1);
        component1.addElement(new MenuItem("fucking", 2));
        component1.addElement(new MenuItem("hell", 2));

        Component component2 = new Menu("中国", 1);
        component2.addElement(new MenuItem("北京", 2));
        component2.addElement(new MenuItem("上海", 2));

        Component component0 = new Menu("world", 0);

        component0.addElement(component1);
        component0.addElement(component2);

        component0.print();
    }
}
