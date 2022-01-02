~~~~

    迭代器模式:
        对数据集合进行迭代

        Interface Iterator:
            hasNext();
            next();
        
        - 迭代器
        Class ConcreteIterator implements Iterator:
            private Collector collector;
            
            - 调用数据集合来获取元素
            public ConcreteIterator(Collector collector) {
                this.collector = collector;
            } 
            
            boolean hashNext();
            Element next();

        - 数据集合
        Class Collector:
            - 获取迭代器
            Iterator getIterator() {
                return new ConcreteIterator(this);
            }
~~~~