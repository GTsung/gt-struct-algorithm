~~~~

    NIO:
        各种I/O事件会注册到Selector中，Selector会从中获取相应的SelectionKey,
    并且从中获取事件及SelectableChannel
        NIO是非I/O阻塞的，这里的非阻塞是指I/O本身，Selector是会阻塞在事件获取中的，
    但一旦I/O操作发生I/O本身不会阻塞，NIO本质是延迟I/O操作到真正发生I/O的时候；
        Selector运行单线程来处理多个Channel，多个通道的每个连接流量都很低的话，使用
    Selector效果会很好    


    传统I/O:
        I/O操作一开始就会堵塞，服务器会为每个I/O操作的客户端分配一个线程，增大了服务
    器的开销

    使用场景:
        当一个服务端需要和多个客户端建立连接传输数据时
        - [NIO](https://www.jianshu.com/p/59f610d8f97d)
        BIO: 需要为每个客户端建立一个线程，单线程的话可能一个客户端阻塞在套接字上导致其他
             准备好的客户端也不能建立连接

~~~~