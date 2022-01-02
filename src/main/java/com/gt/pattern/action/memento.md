~~~~

    备忘录模式:
        为了访问状态而不至于将所有属性都设置成为public权限;
        
        - 原始状态类
        Class Originator:
            private int state;

            createMemento(): 将属性存储在一个备忘录里
            restore(Memento memento): 从备忘录中恢复属性

        - 备忘录
        Class Memento:
            private int state; 与原始状态类拥有相同属性
            Memento(int state) {
                return new Memento(state);
            }
            
            getState();
        
        - Client
        Class Client:
            private Originator originator;
            private LinkedList<Memento> mementos;
            
            void do() {
                Memento memento = originator.createMemento();
                mementos.push(memento);
            }

            void undo() {
                Memento memento = mementos.pop();
                originator.restore(memento);
            }

~~~~