~~~~

    责任链模式:
        将一个请求逐个的经过不同的处理，形成一条链状形式进行处理；
    
        Interface ChainHandler: 
                handle(request)

        Class AbstractChainHandler implements ChainHandler:
                private ChainHandler next;
                handle(request)
                setNext(next)
        
        Class ConcreteChainHandler extends AbstractChainHandler;

        实际工作中可以将获取所有执行链角色及setNext行为放在一个工具类中
~~~~