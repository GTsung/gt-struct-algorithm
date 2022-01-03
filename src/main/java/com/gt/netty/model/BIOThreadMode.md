~~~~~~~



    传统BIO线程模型:
        阻塞IO模式获取输入数据
        每个连接都需要一个独立的线程完成数据输入及业务处理

        当并发数过大时，会创建大量线程，占用很大资源
        当连接创建后，如果无数据传输该线程会阻塞在read操作，浪费资源

        request---->  Thread-->Handler
        request---->  Thread-->Handler
        request---->  Thread-->Handler
        


~~~~~~~