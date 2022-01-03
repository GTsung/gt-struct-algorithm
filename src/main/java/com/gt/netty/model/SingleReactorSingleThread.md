~~~~~~~


    单Reactor单线程模式:
        基于IO多路复用，多个连接共用一个阻塞对象，无需阻塞等待所有链接，当连接有数据传输时，
    操作系统通知应用程序，线程从阻塞状态返回处理业务逻辑；

    
    request --->
    request --->  Selector(select & accept & dispatcher) ---> Thread ---> Handler
    request --->


~~~~~~~~