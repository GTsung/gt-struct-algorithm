package com.gt.pattern.principle.single;

/**
 * 单一职责案例一
 * @author GTsung
 * @date 2022/1/6
 */
public class SingleTwo {

    // 这种虽然符合了单一职责，但是每个交通工具都需要一个单独类，开销太大
    public static void main(String[] args) {
        RoadVehicle vehicle = new RoadVehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");

        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
    }

    static class RoadVehicle {
        public void run(String vehicle) {
            System.out.println(vehicle + "在路上飞驰");
        }
    }

    static class AirVehicle {
        public void run(String vehicle) {
            System.out.println(vehicle + "在天空飞翔");
        }
    }
}

