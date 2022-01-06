package com.gt.pattern.principle.segregation;

/**
 * @author GTsung
 * @date 2022/1/6
 */
public class SegregationOne {

    public static void main(String[] args) {
        // C 依赖了A，A实现了接口，但C只需要用到接口的两个方法，所以此时并非最少隔离
        // 此时需要将原接口进行拆分，将不同的方法分解到不同的接口中
        C c = new C();
        A a = new A();
        c.den1(a);
        c.den2(a);

        D d = new D();
        B b = new B();
        d.den3(b);
        d.den4(b);
        d.den5(b);

        // 改造interface为两个接口 interface1 只有前两个方法 interface2只有后三个方法
        // A实现interface1 B实现interface2
        // C 和 D 各依赖最小接口
    }

    static class A implements Interface1 {
        @Override
        public void o1() {
            System.out.println("A 实现了O1");
        }

        @Override
        public void o2() {
            System.out.println("A 实现了O2");
        }

        @Override
        public void o3() {
            System.out.println("A 实现了O3");
        }

        @Override
        public void o4() {
            System.out.println("A 实现了O4");
        }

        @Override
        public void o5() {
            System.out.println("A 实现了O5");
        }
    }

    static class B implements Interface1 {
        @Override
        public void o1() {
            System.out.println("B 实现了O1");
        }

        @Override
        public void o2() {
            System.out.println("B 实现了O2");
        }

        @Override
        public void o3() {
            System.out.println("B 实现了O3");
        }

        @Override
        public void o4() {
            System.out.println("B 实现了O4");
        }

        @Override
        public void o5() {
            System.out.println("B 实现了O5");
        }
    }

    // C只依赖了接口的o1与o2方法，但
    static class C {
        public void den1(Interface1 i) {
            i.o1();
        }

        public void den2(Interface1 i) {
            i.o2();
        }
    }

    static class D {

        public void den3(Interface1 i) {
            i.o3();
        }

        public void den4(Interface1 i) {
            i.o4();
        }

        public void den5(Interface1 i) {
            i.o5();
        }
    }
}

interface Interface1 {
    void o1();

    void o2();

    void o3();

    void o4();

    void o5();
}