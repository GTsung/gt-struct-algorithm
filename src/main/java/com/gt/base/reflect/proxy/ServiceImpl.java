package com.gt.base.reflect.proxy;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class ServiceImpl implements Service {

    @Override
    public void print(String param) {
        System.out.println("+++" + param + "+++");
    }

    @Override
    public Integer sum() {
        return 2;
    }
}
