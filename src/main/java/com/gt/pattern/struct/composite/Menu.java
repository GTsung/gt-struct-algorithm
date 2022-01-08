package com.gt.pattern.struct.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝
 * @author GTsung
 * @date 2022/1/8
 */
public class Menu extends Component {

    private List<Component> components = new ArrayList<>();

    public Menu(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    protected void print() {
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println(getName());
        for (Component c : components) {
            c.print();
        }
    }

    @Override
    protected void addElement(Component component) {
        components.add(component);
    }

    @Override
    protected void removeElement(Component component) {
        components.remove(component);
    }

    @Override
    protected Component getElement(int index) {
        return components.get(index);
    }
}
