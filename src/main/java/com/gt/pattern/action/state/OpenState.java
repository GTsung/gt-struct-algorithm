package com.gt.pattern.action.state;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class OpenState extends LiftState {

    @Override
    public void open() {
        System.out.println("开启");
    }

    @Override
    public void close() {
        super.context.setLiftState(Context.CLOSE_STATE);
        super.context.close();
    }

    @Override
    public void run() {
        // 开启状态这里不会反应
    }

    @Override
    public void stop() {
        // 同样不反应
    }
}
