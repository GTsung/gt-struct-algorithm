package com.gt.pattern.principle.single;

/**
 * 单一职责案例一
 * @author GTsung
 * @date 2022/1/6
 */
public class SingleOne {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        // 很明显飞机这里违背了单一原则
        vehicle.run("飞机");
    }

    static class Vehicle {
        // run方法不能通用，因此如果传飞机则违背单一原则
        public void run(String vehicle) {
            System.out.println(vehicle + "在路上飞驰");
        }
    }
}

