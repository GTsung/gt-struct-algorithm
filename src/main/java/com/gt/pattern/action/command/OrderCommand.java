package com.gt.pattern.action.command;

import java.util.Map;
import java.util.Set;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class OrderCommand implements Command {

    private Order order;

    private Chef chef;

    public OrderCommand(Order order, Chef chef) {
        this.order = order;
        this.chef = chef;
    }

    @Override
    public void execute() {
        System.out.println(order.getTableNum() + "桌的订单:");
        Map<String, Integer> foodDir = order.getFoodDir();
        Set<String> keys = foodDir.keySet();
        for (String foodName: keys) {
            chef.makeFood(foodName, foodDir.get(foodName));
        }
        System.out.println("done");
    }
}
