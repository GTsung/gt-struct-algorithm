package com.gt.rpc.simple;

public interface Server {

    void register(Class<?> serviceInterface, Class<?> serviceImpl);

    void start();
}
