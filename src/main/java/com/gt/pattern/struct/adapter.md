~~~~

适配器模式:
    1.对象适配器:
        interface---> NewService:newMethod()
        -- 适配器实现接口并聚合旧服务类
        Adapter implements NewService 
            private ServiceClass serviceClass;
        -- 旧服务
        ServiceClass: oldServiceMethod();

    2.类适配器:
        interface---> NewService:newMethod()
        -- 适配器实现新接口并继承旧服务类
        Adapter implements NewService extends ServiceClass
        -- 旧服务
        ServiceClass: oldServiceMethod();

~~~~