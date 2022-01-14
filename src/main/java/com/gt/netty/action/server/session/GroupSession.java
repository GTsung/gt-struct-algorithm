package com.gt.netty.action.server.session;

import io.netty.channel.Channel;

import java.util.List;
import java.util.Set;

public interface GroupSession {

    /**
     * 創建一個聊天組，如果不存在才能創建成功，否則返回null
     * @param name 組名
     * @param members 成員
     * @return
     */
    Group createGroup(String name, Set<String> members);

    /**
     * 加入聊天組
     * @param name
     * @param member
     * @return
     */
    Group joinMember(String name, String member);

    /**
     * 移除組成員
     * @param name
     * @param member
     * @return
     */
    Group removeMember(String name, String member);

    /**
     * 移除聊天組
     * @param name
     * @return
     */
    Group removeGroup(String name);

    /**
     * 獲取組成員
     * @param name
     * @return
     */
    Set<String> getMembers(String name);

    /**
     * 獲取組成員的channel集合，只有在綫的channel才返回
     * @param name
     * @return
     */
    List<Channel> getMembersChannel(String name);

    /**
     * 組是否已經被創建
     * @param name
     * @return
     */
    boolean isCreated(String name);
}
