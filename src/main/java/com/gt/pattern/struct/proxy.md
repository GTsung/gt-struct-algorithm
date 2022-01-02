~~~~

    代理模式:
        1.延迟初始化
        2.访问控制:保护被代理对象
        3.本地执行远程服务
    
    实现:
        ServiceInterface:serviceMethod();
        ServiceImpl implements ServiceInterface

        ServiceProxy implements ServiceInterface
            private ServiceImpl serviceImpl;

    jdk:
        Interface
        ServiceImpl
        JdkProxy:
            private Object target;
            Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), 
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, 
                            Object[] args) throws Throwable {
                        // 加强
                        return method.invoke(target, args);
                    }
            });

    cglib:
        CglibProxy implements MethodInterceptor {
            private Object target;
            public Object getProxy() {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(target.getClass());
                enhancer.setCallback(this);
                return enhancer.create();
            }

            @Overrider
            public Object intercept(Object proxy, Method method, 
                Object[] objects, MethodProxy methodProxy) throws Throwable {
                // 加强
                return method.invoke(target, objects);
            }
        }

~~~~