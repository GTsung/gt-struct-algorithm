package com.gt.netty.action.server.session;

import io.netty.channel.Channel;

public interface Session {

    /**
     * 綁定會話
     * @param channel 哪個channel綁定
     * @param username 會話綁定的用戶
     */
    void bind(Channel channel, String username);

    /**
     * 解除綁定
     * @param channel
     */
    void unbind(Channel channel);

    /**
     * 獲取屬性
     * @param channel
     * @param name 屬性名
     * @return 屬性值
     */
    Object getAttribute(Channel channel, String name);

    /**
     * 設置屬性
     * @param channel
     * @param name
     * @param value
     */
    void setAttribute(Channel channel, String name, Object value);

    /**
     * 根據用戶獲取channel
     * @param username
     * @return
     */
    Channel getChannel(String username);
}
