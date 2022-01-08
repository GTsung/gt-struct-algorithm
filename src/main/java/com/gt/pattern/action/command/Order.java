package com.gt.pattern.action.command;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class Order {

    private int tableNum;

    private Map<String, Integer> foodDir = new HashMap<>();

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public Map<String, Integer> getFoodDir() {
        return foodDir;
    }

    public void setFoodDir(String name, int num) {
        foodDir.put(name, num);
    }
}
