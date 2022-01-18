package com.gt.dstributed.remote.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author GTsung
 * @date 2022/1/18
 */
public class RegistryService {

    public static void main(String[] args) {
        try {
            // 本地主機上的遠程對象注冊表Registry的實例
            Registry registry = LocateRegistry.createRegistry(1090);
            // 創建一個遠程對象
            HelloRegistryFacade helloRegistryFacade = new HelloRegistryFacadeImpl();
            // 遠程對象注冊到RMI注冊服務器
            registry.bind("HelloRegistry", helloRegistryFacade);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
