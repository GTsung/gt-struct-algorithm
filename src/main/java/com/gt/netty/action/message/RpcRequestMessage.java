package com.gt.netty.action.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author GTsung
 * @date 2022/1/15
 */
@Data
@ToString(callSuper = true)
public class RpcRequestMessage extends Message {

    /**
     * 調用的接口全限定名，服務端根據它找到實現
     */
    private String interfaceName;
    /**
     * 接口方法名
     */
    private String methodName;
    /**
     * 方法返回類型
     */
    private Class<?> returnType;
    /**
     * 方法參數類型
     */
    private Class[] parameterTypes;
    /**
     * 方法參數值
     */
    private Object[] parameterValue;

    public RpcRequestMessage(int sequenceId, String interfaceName, String methodName, Class<?> returnType,
                             Class[] parameterTypes, Object[] parameterValue) {
        super.setSequenceId(sequenceId);
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.parameterValue = parameterValue;
    }

    @Override
    public int getMessageType() {
        return RPC_MESSAGE_TYPE_REQUEST;
    }
}
