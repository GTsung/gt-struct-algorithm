package com.gt.netty.action.server.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author GTsung
 * @date 2022/1/14
 */
public class UserServiceMemoryImpl implements UserService {

    private Map<String, String> userMap = new ConcurrentHashMap<>();

    {
        userMap.put("專業操劉蕭媳婦", "000");
        userMap.put("專業操劉蕭老母", "100");
        userMap.put("專業操劉蕭gay", "120");
        userMap.put("專業操劉蕭妹妹", "110");
        userMap.put("fucking", "Liu");
    }

    @Override
    public boolean login(String username, String password) {
        String pass = userMap.get(username);
        return password.equals(pass);
    }
}
