
~~~~

    命令模式:
        当请求发送一系列的命令时会有不同的响应，这个时候需要将命令抽象出来；
        
        Interface CommandInterface:
            execute() ----> 执行命令
        
        - 具体命令
        Class CommandOne implements CommandInterface:
            private Receiver receiver;
            private Params params;
            
            execute(): 调用成员receiver的具体响应方法
        
        - 响应者    
        Class Receiver: operation();

        - 发送命令者
        Class Send:
            private CommandInterface command;
            setCommand(command);
            executeCommand();
        
~~~~