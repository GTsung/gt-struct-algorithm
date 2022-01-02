~~~~

组合模式:
    通常用于树状的结构，例如盒子与产品的总价格，盒子中可以包含另外的盒子，
也可以包含产品

    -- 接口
    Component: execute()
    
    -- 叶子
    class Leaf implements Component

    -- 非叶子
    class Composite implements Component
        -- 包含其他Component
        List<Component> components; 
        
        -- 其他方法
        addComponent();
        removeComponent();
        getChildren();
        execute();
~~~~