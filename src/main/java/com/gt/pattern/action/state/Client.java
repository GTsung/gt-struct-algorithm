package com.gt.pattern.action.state;

import org.checkerframework.checker.units.qual.C;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class Client {
    public static void main(String[] args) {
        Context context= new Context();

        context.setLiftState(new RunState());

        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
