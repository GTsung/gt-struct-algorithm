package com.gt.netty.action.server.service;

/**
 * @author GTsung
 * @date 2022/1/15
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String msg) {
        return "hello";
    }
}
