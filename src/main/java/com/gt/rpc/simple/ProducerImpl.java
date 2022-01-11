package com.gt.rpc.simple;

/**
 * @author GTsung
 * @date 2022/1/11
 */
public class ProducerImpl implements Producer {

    @Override
    public String sayHello() {
        return "hello world, from rpc";
    }
}
