package com.gt.pattern.principle.single;

/**
 * 单一职责案例一
 * @author GTsung
 * @date 2022/1/6
 */
public class SingleThree {

    public static void main(String[] args) {
        RoadVehicle vehicle = new RoadVehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");


        vehicle.runAir("飞机");
    }

    // 在某种程度未在类这个层次上完全遵守单一职责
    // 但在方法层次上遵守了单一职责(这个是因为当前vehicle类的方法数量较少，使用多个类开销太大)

    static class RoadVehicle {

        public void run(String vehicle) {
            System.out.println(vehicle + "在路上飞驰");
        }

        public void runAir(String vehicle) {
            System.out.println(vehicle + "在天空飞");
        }
    }

}

