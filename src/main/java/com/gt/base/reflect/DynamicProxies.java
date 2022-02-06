package com.gt.base.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author GTsung
 * @date 2022/2/7
 */
public class DynamicProxies {

    public static void main(String[] args) {
        MyGod proxy = (MyGod) Proxy.newProxyInstance(MyGod.class.getClassLoader(),
                new Class[]{MyGod.class}, new MyInvocation());
    }

    static class MyInvocation implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }

    interface MyGod {
        void display();
    }
}
