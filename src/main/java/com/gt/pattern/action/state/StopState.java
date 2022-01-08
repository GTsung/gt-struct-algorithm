package com.gt.pattern.action.state;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class StopState extends LiftState {
    @Override
    public void open() {
        super.context.setLiftState(Context.OPEN_STATE);
        super.context.open();
    }

    @Override
    public void close() {
        super.context.setLiftState(Context.CLOSE_STATE);
        super.context.close();
    }

    @Override
    public void run() {
        super.context.setLiftState(Context.RUN_STATE);
        super.context.run();
    }

    @Override
    public void stop() {
        System.out.println("停止");
    }
}
