~~~~~

    模板方法模式:
        如果不同的类的行为相同，且执行这些行为的顺序一致，则可以抽取出来

        AbstractClass:
            
            protect abstract void add();
            protect abstract void mulity();
            protect abstract void divide();

            public void result() {
                add();
                mulity();
                divide();
            }

        ConcreteClass extends AbstractClass:
            void add() {
                // add
            }

            void mulity() {
                // mulity
            }

            void divide() {
                // divide
            }
~~~~~