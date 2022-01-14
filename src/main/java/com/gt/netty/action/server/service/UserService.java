package com.gt.netty.action.server.service;

/**
 * @author GTsung
 * @date 2022/1/14
 */
public interface UserService {

    /**
     * 登錄
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);
}
