package com.gt.netty.action.server.session;

/**
 * @author GTsung
 * @date 2022/1/14
 */
public abstract class SessionFactory {

    private static Session session = new SessionMemoryImpl();

    public static Session getSession() {
        return session;
    }
}
