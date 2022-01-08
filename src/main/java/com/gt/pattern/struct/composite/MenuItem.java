package com.gt.pattern.struct.composite;

/**
 * 树叶
 *
 * @author GTsung
 * @date 2022/1/8
 */
public class MenuItem extends Component {

    public MenuItem(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    protected void print() {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println(getName());
    }
}
