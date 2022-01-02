~~~~

    中介者模式:
        交给中介

        Interface Meditor:
            reactOnA();
            reactOnB();
            reactOnC();

        - 中介者
        Class ConcreteMeditor implements Meditor:
            ComponentA componentA;
            ComponentB componentB;
            ComponentC componentC;

            reactOnA()----> if sender == componentA
            reactOnB()
            reactOnC()
            
        - 组件
        Class ComponentA:
            Meditor meditor;
            operationA(); ----> 调用meditor中对应的A方法
            

~~~~