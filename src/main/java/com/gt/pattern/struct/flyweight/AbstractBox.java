package com.gt.pattern.struct.flyweight;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public abstract class AbstractBox {

    public abstract String getShape() ;

    public void display(String color) {
        System.out.println("形状:" + getShape() + ",颜色:" + color);
    }
}
