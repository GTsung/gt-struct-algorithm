package com.gt.base.reflect.proxy;

import java.lang.reflect.Proxy;

/**
 * @author GTsung
 * @date 2022/3/26
 */
public class ProxyTest {

    public static void main(String[] args) {
        proxyUtil();
        test();
    }

    private static void test() {
        Service o = (Service) Proxy.newProxyInstance(ServiceImpl.class.getClassLoader(), ServiceImpl.class.getInterfaces(),
                (proxy, method, args1) -> method.invoke(ServiceImpl.class.newInstance(), args1));
        System.out.println(o);
        o.print("as");
    }

    private static void proxyUtil() {
        ProxyUtil proxyUtil = new ProxyUtil(new ServiceImpl());
        Service proxy = (Service) proxyUtil.getProxy();
        System.out.println(proxy);
        proxy.print("11");

        Integer sum = proxy.sum();
        System.out.println(sum);
    }
}
