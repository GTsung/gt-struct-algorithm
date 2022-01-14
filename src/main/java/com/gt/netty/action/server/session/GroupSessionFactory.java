package com.gt.netty.action.server.session;

/**
 * @author GTsung
 * @date 2022/1/14
 */
public abstract class GroupSessionFactory {

    private static GroupSession session = new GroupSessionMemoryImpl();

    public static GroupSession getGroupSession() {
        return session;
    }
}
