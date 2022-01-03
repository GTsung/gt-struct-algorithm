~~~~~~~~~



    单reactor多线程模型:

        request ---->                                           Thread ---> Handler (数据读取发送、业务处理)
        request ---->   Reactor (selector & dispatcher) ---->   Thread ---> Handler
        request ---->                                           Thread ---> Handler




~~~~~~~~~