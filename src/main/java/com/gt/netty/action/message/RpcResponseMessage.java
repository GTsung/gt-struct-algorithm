package com.gt.netty.action.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author GTsung
 * @date 2022/1/15
 */
@Data
@ToString(callSuper = true)
public class RpcResponseMessage extends Message {

    /**
     * 返回值
     */
    private Object returnValue;
    /**
     * 異常值
     */
    private Exception exceptionValue;

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_RESPONSE;
    }
}
