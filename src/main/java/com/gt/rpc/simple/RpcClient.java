package com.gt.rpc.simple;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author GTsung
 * @date 2022/1/11
 */
public class RpcClient {

    public Object doService(final Class<?> serviceClass, final InetSocketAddress address) {
        return Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass},
                (proxy, method, args) -> {
                    Socket socket = new Socket();
                    socket.connect(address);
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.writeUTF(serviceClass.getName());
                    out.writeUTF(method.getName());
                    out.writeObject(method.getParameterTypes());
                    out.writeObject(args);
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    return in.readObject();
                });
    }
}
