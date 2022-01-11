package com.gt.rpc.simple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author GTsung
 * @date 2022/1/11
 */
public class ServerCenter implements Server {

    private final static Map<String, Class> SERVICE_MAP = new ConcurrentHashMap<>();

    private static ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private int port;

    public ServerCenter(int port) {
        super();
        this.port = port;
    }

    @Override
    public void register(Class<?> serviceInterface, Class<?> serviceImpl) {
        SERVICE_MAP.put(serviceInterface.getName(), serviceImpl);
    }

    @Override
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(port));
            while (true) {
                es.submit(new RpcTask(serverSocket.accept()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class RpcTask implements Runnable {
        private Socket socket;

        public RpcTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectInputStream in = null;
            ObjectOutputStream out = null;
            try {
                in = new ObjectInputStream(socket.getInputStream());
                String serviceName = in.readUTF();
                String methodName = in.readUTF();

                Class<?>[] parameterizedType = (Class<?>[]) in.readObject();
                Object[] args = (Object[]) in.readObject();

                Class serviceImpl = SERVICE_MAP.get(serviceName);
                if (Objects.isNull(serviceImpl)) {
                    throw new ClassNotFoundException();
                }

                Method method = serviceImpl.getMethod(methodName, parameterizedType);
                Object result = method.invoke(serviceImpl.newInstance(), args);
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(result);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
