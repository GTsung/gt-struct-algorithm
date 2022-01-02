~~~~

单例模式:
    多次调用只产生一个实例

class Singleton {
    private Singleton(){}

    private static class SingletonInstance {
        private static Singleton single = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInstance.single;
    }
}

~~~~