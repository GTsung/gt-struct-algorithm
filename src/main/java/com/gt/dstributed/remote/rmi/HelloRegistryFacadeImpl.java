package com.gt.dstributed.remote.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 服務端實現類
 * @author GTsung
 * @date 2022/1/18
 */
public class HelloRegistryFacadeImpl extends UnicastRemoteObject implements HelloRegistryFacade {

    public HelloRegistryFacadeImpl() throws RemoteException {
        super();
    }

    @Override
    public String helloWorld(String name) throws RemoteException {
        return "let's " + name;
    }
}
