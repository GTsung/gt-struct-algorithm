~~~~
抽象工厂模式,将工厂进行抽象
abstractFactory:
    createChair();
    createBed();

-- 以下是两种不同风格的工厂
---> ModernFactory:createChair();createBed();
---> OldFactory:createChair();createBed();

-- 产品类也可以分为不同种类的
Chair: ModernChair | OldChair
Bed: ModernBed | OldBed

(1)同一工厂生成的产品一致
(2)单一职责
(3)开闭原则
(4)引入过多类，使得代码复杂
~~~~