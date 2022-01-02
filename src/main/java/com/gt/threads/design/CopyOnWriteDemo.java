package com.gt.threads.design;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author GTsung
 * @date 2021/12/28
 */
public class CopyOnWriteDemo {

    /**
     * CopyOnWrite是set remove加锁的 get不加锁
     * 迭代器保留array的副本，可能再array更新时与副本不同
     * 每次set与remove都会移动数组
     *
     * @param args
     */

    public static void main(String[] args) {
        router();
    }


    private static void router() {
        Router router = new Router("192.178.1.1", 7000, "ServiceA");
        RouterTable routerTable = new RouterTable();
        routerTable.add(router);
        Set<Router> routerSet = routerTable.get(router.iface);
        routerSet.forEach(System.out::println);
    }

    /**
     * 路由信息
     */
    private static final class Router {
        private final String ip;
        private final Integer port;
        private final String iface;

        public Router(String ip, Integer port, String iface) {
            this.ip = ip;
            this.port = port;
            this.iface = iface;
        }

        @Override
        public int hashCode() {
            return 31 * ip.hashCode() + 31 * port.hashCode() + 31 * iface.hashCode();
        }

        @Override
        public boolean equals(Object obj) {

            if (obj instanceof Router) {
                Router r = (Router) obj;
                return iface.equals(r.iface)
                        && port.equals(r.port)
                        && ip.equals(r.ip);
            }
            return false;
        }

        @Override
        public String toString() {
            return "[iface=\'" + iface + "\', ip=\'" + ip + "\', port=" + port + "]";
        }
    }

    /**
     * RPC中的服务路由表，这个路由表用于客户端负载均衡获取其中一个服务，另外服务端上下线的更新
     * 服务上下线更新并不是那么频繁，因此保存路由信息使用COW模式
     */
    private static class RouterTable {
        // 使用了COW
        Map<String, CopyOnWriteArraySet<Router>> routerMap = new ConcurrentHashMap<>();

        public Set<Router> get(String iface) {
            return routerMap.get(iface);
        }

        public void remove(Router router) {
            if (routerMap.get(router.iface) != null) {
                routerMap.remove(router.iface);
            }
        }

        public void add(Router router) {
            Set<Router> set = routerMap.computeIfAbsent(router.iface, r -> new CopyOnWriteArraySet<>());
            set.add(router);
        }
    }

}
