~~~~~~

    访问者模式:
        将元素的访问方式交给访问者

        Elelment:
            void accept(Visitor visitor);

        ElementA implements Element:
            void accept(Visitor visitor) {
                visitor.visit(this);
            }

        ElementB implements Element:
            void accept(Visitor visitor) {
                visitor.visit(this);
            }

        Interface Visitor:
            visit(ElementA a);
            visit(ElementB b);

~~~~~~