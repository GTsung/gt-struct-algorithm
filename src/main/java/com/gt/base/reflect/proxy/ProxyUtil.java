package com.gt.base.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class ProxyUtil {

    private Object source;

    public ProxyUtil(Object source) {
        this.source = source;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(source.getClass().getClassLoader(),
                source.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(source, args);
                    }
                });
    }
}
