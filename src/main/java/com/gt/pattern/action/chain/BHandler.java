package com.gt.pattern.action.chain;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class BHandler extends AbstractHandler {

    public BHandler() {
        super(0, AbstractHandler.LEAVE_SEVEN);
    }

    @Override
    protected void handlerLeave(LeaveRequest leaveRequest) {
        System.out.println(leaveRequest.getName() + "请假" + leaveRequest.getDay() + "天，" +
                "原因:" + leaveRequest.getReason());
        System.out.println("批准");
    }
}
