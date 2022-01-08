package com.gt.pattern.action.state;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class RunState extends LiftState {

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void run() {
        System.out.println("运行");
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.STOP_STATE);
        super.context.stop();
    }
}
