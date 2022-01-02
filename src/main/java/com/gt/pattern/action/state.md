~~~~~

    状态模式:
        为对象所有的状态新建一个类，将所有状态的对应行为抽取到这些类中；
        
        状态机模型: 一个对象在不同情况下具有不同状态，也会有不同行为
        将这些状态抽取出来，对应的行为也抽取出来；

        - 状态基类:
            AbstractState:
                abstract void on();
                abstract void off();

        - 状态子类
            Class OnState implements AbstractState

        - 对象
            Class Context:
                AbstractState state;
                void do() {
                    state.on();
                }
                

~~~~~