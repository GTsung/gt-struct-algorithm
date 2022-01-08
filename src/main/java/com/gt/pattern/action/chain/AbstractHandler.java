package com.gt.pattern.action.chain;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public abstract class AbstractHandler {

    public static final int LEAVE_ONE = 0;
    public static final int LEAVE_THREE = 3;
    public static final int LEAVE_SEVEN = 7;

    // 可以审批的假期天数区间
    private int start;
    private int end;

    private AbstractHandler next;


    protected AbstractHandler(int start) {
        this.start = start;
    }

    protected AbstractHandler(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void setNext(AbstractHandler next) {
        this.next = next;
    }

    // 处理
    protected abstract void handlerLeave(LeaveRequest leaveRequest) ;

    // 提交至上级
    protected final void submit(LeaveRequest leaveRequest) {

        this.handlerLeave(leaveRequest);
        if (this.next != null && this.end< leaveRequest.getDay()) {
            this.next.handlerLeave(leaveRequest);
        } else {
            System.out.println("处理完毕");
        }
    }

}
