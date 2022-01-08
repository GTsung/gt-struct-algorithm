package com.gt.pattern.action.chain;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class AHandler extends AbstractHandler {

    public AHandler() {
        super(0, AbstractHandler.LEAVE_THREE);
    }

    @Override
    protected void handlerLeave(LeaveRequest leaveRequest) {
        System.out.println(leaveRequest.getName() + "请假" + leaveRequest.getDay() + "天，" +
                "原因:" + leaveRequest.getReason());
        System.out.println("批准");
    }
}
