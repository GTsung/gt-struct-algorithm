~~~~

    享元模式:
        程序中出现大量相似的对象，而且这些相似对象中包含可抽取
    且能在多个对象间共享的重复状态。

    实现:
        1.将需要修改为共享对象的类成员变量分类:
            内在状态:包含不变的、可在许多对象中重复使用的成员变量
            外在状态:包含每个对象各自不同的情景数据的成员变量
        2.保留内在状态的变量，将其设置为不可修改；这些变量仅可在构造函数中
          获得初始值
        


~~~~