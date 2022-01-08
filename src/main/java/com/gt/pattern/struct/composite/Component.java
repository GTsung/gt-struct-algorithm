package com.gt.pattern.struct.composite;

/**
 * 组合模式的基本元素，一般用于树状结构
 * @author GTsung
 * @date 2022/1/8
 */
public abstract class Component {

    protected String name;
    protected int level;

    public String getName() {
        return name;
    }

    protected abstract void print();


    /**
     * 以下方法可以直接写到menu中
     * @param component
     */
    protected void addElement(Component component) {
        throw new RuntimeException();
    }

    protected void removeElement(Component component) {
        throw new RuntimeException();
    }

    protected Component getElement(int index) {
        throw new RuntimeException();
    }

}
