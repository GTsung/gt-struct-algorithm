package com.gt.pattern.action.chain;

/**
 * 请假
 * @author GTsung
 * @date 2022/1/8
 */
public class LeaveRequest {

    private String name;

    private String reason;

    private int day;

    public LeaveRequest(String name, String reason, int day) {
        this.name = name;
        this.reason = reason;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
