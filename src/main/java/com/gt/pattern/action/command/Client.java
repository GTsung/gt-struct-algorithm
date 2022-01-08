package com.gt.pattern.action.command;

/**
 * 命令模式: 调用者与执行者解耦
 * @author GTsung
 * @date 2022/1/8
 */
public class Client {

    public static void main(String[] args) {

        Order order = new Order();
        order.setTableNum(0);
        order.setFoodDir("rice", 1);
        // 命令对象
        Command command = new OrderCommand(order, new Chef());
        Waitor waitor = new Waitor();
        waitor.setCommand(command);
        waitor.orderUp();
    }
}
