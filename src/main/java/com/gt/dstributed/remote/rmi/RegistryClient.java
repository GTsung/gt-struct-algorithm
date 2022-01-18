package com.gt.dstributed.remote.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author GTsung
 * @date 2022/1/18
 */
public class RegistryClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1090);
            HelloRegistryFacade helloService = (HelloRegistryFacade) registry.lookup("HelloRegistry");
            String response = helloService.helloWorld("fuck LiuXiao's mother");
            System.out.println("======"+ response + "=====");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
