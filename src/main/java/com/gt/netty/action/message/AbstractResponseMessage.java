package com.gt.netty.action.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author GTsung
 * @date 2022/1/15
 */
@Data
@ToString(callSuper = true)
public abstract class AbstractResponseMessage extends Message {

    private boolean success;
    private String reason;

    public AbstractResponseMessage(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }

    public AbstractResponseMessage() {
    }
}
