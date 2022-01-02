~~~~

    策略模式:
        AbstractClass:
            private Map<String, AbstractClass> map = new ConcurrentHashMap<>();

            protect void register(String str, Class clazz) {
                map.put(str, clazz);
            }
            
            public void doSth(String str) {
                AbstractClass clazz = map.get(str);
                clazz.doReal();
            }

            protect abstract void doReal();
        }

        AClass:
            public AClass() {
                super();
                register("A", this);
            }

            protect void doReal() {
                // do sth...
            }
        }

~~~~