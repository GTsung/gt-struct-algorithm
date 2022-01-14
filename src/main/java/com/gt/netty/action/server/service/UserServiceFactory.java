package com.gt.netty.action.server.service;

/**
 * @author GTsung
 * @date 2022/1/14
 */
public abstract class UserServiceFactory {

    private static UserService userService = new UserServiceMemoryImpl();

    public static UserService getUserService() {
        return userService;
    }

}
