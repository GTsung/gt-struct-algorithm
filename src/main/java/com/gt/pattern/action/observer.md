~~~~

    观察者模式:
        发布订阅，解耦

        Class Sender:
            private List<Observer> observers;

            void add(Observer observer):observers.add(observer);
            void delete(Observer observer):observers.remove(observer);

            void notify() {
                // 循环调用observer.update();
            }

        - 订阅者
        Interface Observer: update();
        Class ConcreteObserver implements Observer


~~~~