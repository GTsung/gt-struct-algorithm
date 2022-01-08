package com.gt.pattern.action.chain;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class Client {
    public static void main(String[] args) {
        LeaveRequest leaveRequest  = new LeaveRequest("adams", "marry", 2);

        BHandler bHandler = new BHandler();
        AHandler aHandler = new AHandler();
        aHandler.setNext(bHandler);

        aHandler.submit(leaveRequest);
    }
}
