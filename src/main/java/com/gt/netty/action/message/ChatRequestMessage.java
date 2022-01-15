package com.gt.netty.action.message;

import lombok.Data;
import lombok.ToString;

/**
 * @author GTsung
 * @date 2022/1/15
 */
@Data
@ToString(callSuper = true)
public class ChatRequestMessage extends Message {

    private String content;

    private String to;

    private String from;

    public ChatRequestMessage(String content, String to, String from) {
        this.content = content;
        this.to = to;
        this.from = from;
    }

    public ChatRequestMessage() {
    }

    @Override
    public int getMessageType() {
        return ChatRequestMessage;
    }
}
