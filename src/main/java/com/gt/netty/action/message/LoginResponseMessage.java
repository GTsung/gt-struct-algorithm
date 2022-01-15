package com.gt.netty.action.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author GTsung
 * @date 2022/1/15
 */
@Data
@ToString(callSuper = true)
public class LoginResponseMessage extends AbstractResponseMessage {

    public LoginResponseMessage(boolean success, String reason) {
        super(success, reason);
    }

    @Override
    public int getMessageType() {
        return LoginResponseMessage;
    }
}
