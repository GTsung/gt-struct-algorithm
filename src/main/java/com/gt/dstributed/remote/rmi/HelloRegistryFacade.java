package com.gt.dstributed.remote.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 服務端
 * @author GTsung
 * @date 2022/1/18
 */
public interface HelloRegistryFacade extends Remote {

    String helloWorld(String name) throws RemoteException;

}
